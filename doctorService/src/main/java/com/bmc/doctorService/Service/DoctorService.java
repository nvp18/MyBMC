package com.bmc.doctorService.Service;

import com.bmc.doctorService.DTO.ApproverDTO;
import com.bmc.doctorService.DTO.DoctorDTO;
import com.bmc.doctorService.DTO.DoctorRegistrationDTO;
import com.bmc.doctorService.Entity.Doctor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorService {

    DoctorRegistrationDTO collectDoctorInfo(Doctor doctor);

    String uploadDocuments(String id, MultipartFile[] files) throws Exception;

    DoctorDTO approveDoctor(ApproverDTO approverDTO, String doctorId);

    DoctorDTO rejectDoctor(ApproverDTO approverDTO, String doctorId);

    List<DoctorDTO> get20Doctors(String status,String speciality);

    DoctorDTO getDoctorDetails(String doctorId);

}
