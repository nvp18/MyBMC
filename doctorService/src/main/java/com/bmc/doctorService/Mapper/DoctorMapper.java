package com.bmc.doctorService.Mapper;

import com.bmc.doctorService.DTO.DoctorDTO;
import com.bmc.doctorService.DTO.DoctorRegistrationDTO;
import com.bmc.doctorService.Entity.Doctor;

public class DoctorMapper {

    public static Doctor convertDoctorRegistrationDTOToDoctorEntity(DoctorRegistrationDTO doctorRegistrationDTO) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorRegistrationDTO.getFirstName());
        doctor.setLastName(doctorRegistrationDTO.getLastName());
        doctor.setDob(doctorRegistrationDTO.getDob());
        doctor.setEmailId(doctorRegistrationDTO.getEmailId());
        doctor.setMobile(doctorRegistrationDTO.getMobile());
        doctor.setPan(doctorRegistrationDTO.getPan());
        if(doctorRegistrationDTO.getSpeciality()!=null){
            doctor.setSpeciality(doctorRegistrationDTO.getSpeciality());
        }
        return doctor;
    }

    public static DoctorRegistrationDTO convertDoctorEntityToDoctorRegistrationDTO(Doctor doctor) {
        DoctorRegistrationDTO doctorRegistrationDTO = new DoctorRegistrationDTO();
        doctorRegistrationDTO.setDob(doctor.getDob());
        doctorRegistrationDTO.setEmailId(doctor.getEmailId());
        doctorRegistrationDTO.setFirstName(doctor.getFirstName());
        doctorRegistrationDTO.setMobile(doctor.getMobile());
        doctorRegistrationDTO.setLastName(doctor.getLastName());
        doctorRegistrationDTO.setPan(doctor.getPan());
        doctorRegistrationDTO.setSpeciality(doctor.getSpeciality());
        doctorRegistrationDTO.setId(doctor.get_id());
        doctorRegistrationDTO.setRegistrationDate(doctor.getRegistrationDate());
        doctorRegistrationDTO.setStatus(doctor.getStatus());
        return doctorRegistrationDTO;
    }

    public static DoctorDTO convertDoctorEntityToDoctorDTO(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.get_id());
        doctorDTO.setStatus(doctor.getStatus());
        doctorDTO.setSpeciality(doctor.getSpeciality());
        doctorDTO.setPan(doctor.getPan());
        doctorDTO.setMobile(doctor.getMobile());
        doctorDTO.setLastName(doctor.getLastName());
        doctorDTO.setDob(doctor.getDob());
        doctorDTO.setFirstName(doctor.getFirstName());
        doctorDTO.setEmailId(doctor.getEmailId());
        doctorDTO.setRegistrationDate(doctor.getRegistrationDate());
        doctorDTO.setApprovedBy(doctor.getApprovedBy());
        doctorDTO.setApproverComments(doctor.getApproverComments());
        doctorDTO.setVerificationDate(doctor.getVerificationDate());
        return doctorDTO;
    }
}
