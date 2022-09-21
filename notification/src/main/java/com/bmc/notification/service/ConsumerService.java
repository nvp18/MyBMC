package com.bmc.notification.service;

import com.bmc.appointmentService.DTO.AppointmentDTO;
import com.bmc.appointmentService.DTO.PrescriptionDTO;
import com.bmc.doctorService.DTO.DoctorDTO;
import com.userService.user.DTO.UserDTO;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private SesEmailVerificationService emailVerificationService;

    @KafkaListener(topics = "${user.topic}",groupId = "${user.group.id}", containerFactory = "concurrentKafkaListenerContainerFactoryforUser")
    public void userListen(@Payload List<UserDTO> userList) {
        for(UserDTO user : userList){
            String emailId = user.getEmailId();
            System.out.println(user);
            emailVerificationService.verifyEmail(emailId);
        }
    }

    @KafkaListener(topics = "${doctor.topic}",groupId = "${doctor.group.id}", containerFactory = "concurrentKafkaListenerContainerFactoryForDoctor")
    public void doctorListen(@Payload List<DoctorDTO> doctorDTOS) {
        for(DoctorDTO doctorDTO: doctorDTOS){
            String emailId = doctorDTO.getEmailId();
            System.out.println(doctorDTO);
            emailVerificationService.verifyEmail(emailId);
        }
    }

    @KafkaListener(topics = "${doctorApproval.topic}",groupId = "${doctorApproval.group.id}", containerFactory = "concurrentKafkaListenerContainerFactoryForDoctor")
    public void doctorApprovalListen(@Payload List<DoctorDTO> doctorDTOS) throws TemplateException, IOException {
        for(DoctorDTO doctorDTO: doctorDTOS){
            System.out.println(doctorDTO);
            emailVerificationService.sendApprovalMail(doctorDTO);
        }
    }

    @KafkaListener(topics = "${doctorReject.topic}",groupId = "${doctorreject.group.id}", containerFactory = "concurrentKafkaListenerContainerFactoryForDoctor")
    public void doctorRejectListen(@Payload List<DoctorDTO> doctorDTOS) throws TemplateException, IOException {
        for(DoctorDTO doctorDTO: doctorDTOS){
            System.out.println(doctorDTO);
            emailVerificationService.sendRejectMail(doctorDTO);
        }
    }

    @KafkaListener(topics = "${prescription.topic}",groupId = "${prescription.group.id}", containerFactory = "concurrentKafkaListenerContainerFactoryForPrescription")
    public void appointmentListen(@Payload PrescriptionDTO prescriptionDTO) throws TemplateException, IOException {
        emailVerificationService.sendPrescriptionMail(prescriptionDTO);
    }

    @KafkaListener(topics = "${appointment.topic}",groupId = "${appointment.group.id}",containerFactory = "concurrentKafkaListenerContainerFactoryForAppointment")
    public void sendAppointmentDetails(@Payload AppointmentDTO appointmentDTO) throws TemplateException, IOException {
        emailVerificationService.sendAppointmentMail(appointmentDTO);
    }
}
