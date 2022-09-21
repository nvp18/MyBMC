package com.bmc.doctorService.Service.Impl;

import com.bmc.doctorService.Config.S3Repository;
import com.bmc.doctorService.DAO.DoctorDAO;
import com.bmc.doctorService.DTO.ApproverDTO;
import com.bmc.doctorService.DTO.DoctorDTO;
import com.bmc.doctorService.DTO.DoctorRegistrationDTO;
import com.bmc.ratingservice.DTO.RatingDTO;
import com.bmc.doctorService.Entity.Doctor;
import com.bmc.doctorService.Exception.ResourceNotFoundException;
import com.bmc.doctorService.Mapper.DoctorMapper;
import com.bmc.doctorService.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private S3Repository s3Repository;

    @Autowired
    private KafkaTemplate<String, DoctorDTO> kafkaTemplate;

    @Value("${doctorVerification.topic}")
    private String kafkaTopic;

    @Value("${doctorApproval.topic}")
    private String doctorApprovalTopic;

    @Value("${doctorReject.topic}")
    private String doctorRejectTopic;

    @Override
    public DoctorRegistrationDTO collectDoctorInfo(Doctor doctor) {
        doctor.setStatus("Pending");
        doctor.setRegistrationDate(new Date());
        Doctor savedDoctor = doctorDAO.save(doctor);
        DoctorRegistrationDTO res = DoctorMapper.convertDoctorEntityToDoctorRegistrationDTO(savedDoctor);
        DoctorDTO doctorDTO = DoctorMapper.convertDoctorEntityToDoctorDTO(savedDoctor);
        sendVerificationEmail(doctorDTO);
        return res;
    }

    @Override
    public String uploadDocuments(String id, MultipartFile[] files) throws Exception {
        for (MultipartFile multipartFile : files) {
            s3Repository.uploadFiles(id, multipartFile);
        }
        return "File(s) uploaded successfully";
    }

    @Override
    public DoctorDTO approveDoctor(ApproverDTO approverDTO, String doctorId) {
        Optional<Doctor> doctorObj = doctorDAO.findById(doctorId);
        if(doctorObj.isPresent()) {
            Doctor doctor = doctorObj.get();
            doctor.setStatus("Active");
            doctor.setApprovedBy(approverDTO.getApprovedBy());
            doctor.setApproverComments(approverDTO.getApproverComments());
            doctor.setVerificationDate(new Date());
            Doctor savedDoctor = doctorDAO.save(doctor);
            DoctorDTO res = DoctorMapper.convertDoctorEntityToDoctorDTO(savedDoctor);
            sendAprrovalEmail(res);
            return res;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public DoctorDTO rejectDoctor(ApproverDTO approverDTO,String doctorId) {
        Optional<Doctor> doctorObj = doctorDAO.findById(doctorId);
        if(doctorObj.isPresent()) {
            Doctor doctor = doctorObj.get();
            doctor.setStatus("Rejected");
            doctor.setApprovedBy(approverDTO.getApprovedBy());
            doctor.setApproverComments(approverDTO.getApproverComments());
            Doctor savedDoctor = doctorDAO.save(doctor);
            DoctorDTO res = DoctorMapper.convertDoctorEntityToDoctorDTO(savedDoctor);
            sendRejectEmail(res);
            return res;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public List<DoctorDTO> get20Doctors(String status,String speciality) {
        List<Doctor> doctors = new ArrayList<>();
        if(!speciality.equals("NOT_GIVEN")) {
            doctors = doctorDAO.findByStatusAndSpeciality(status, speciality);
        } else {
            doctors = doctorDAO.findByStatus(status);
        }
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(int i=0;i<20;i++){
            if(doctors.size()==i){
                break;
            }
            Doctor doctor = doctors.get(i);
            doctorDTOS.add(DoctorMapper.convertDoctorEntityToDoctorDTO(doctor));
        }
        return doctorDTOS;
    }

    @Override
    public DoctorDTO getDoctorDetails(String doctorId) {
        Optional<Doctor> doctor = doctorDAO.findById(doctorId);
        if(doctor.isPresent()){
            DoctorDTO doctorDTO = DoctorMapper.convertDoctorEntityToDoctorDTO(doctor.get());
            return doctorDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @KafkaListener(topics = "${rating.topic}",groupId = "${rating.group.id}", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void doctorListen(@Payload RatingDTO ratingDTO) {
        Optional<Doctor> doctor = doctorDAO.findById(ratingDTO.getDoctorId());
        if(doctor.isPresent()){
            Doctor doctorObj = doctor.get();
            doctorObj.setRatedBy(doctorObj.getRatedBy()+1);
            Float rating = doctorObj.getRating();
            rating = (rating+ratingDTO.getRating())/doctorObj.getRatedBy();
            doctorObj.setRating(rating);
            doctorDAO.save(doctorObj);
        }
    }

    private void sendVerificationEmail(DoctorDTO doctorDTO){
        kafkaTemplate.send(kafkaTopic,doctorDTO);
    }

    private void sendAprrovalEmail(DoctorDTO doctorDTO){
        kafkaTemplate.send(doctorApprovalTopic,doctorDTO);
    }

    private void sendRejectEmail(DoctorDTO doctorDTO){
        kafkaTemplate.send(doctorRejectTopic,doctorDTO);
    }
}
