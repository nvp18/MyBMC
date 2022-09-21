package com.bmc.appointmentService.Mapper;

import com.bmc.appointmentService.DTO.PrescriptionDTO;
import com.bmc.appointmentService.Entity.Prescription;

public class PrescriptionMapper {

    public static Prescription convertDTOToEntity(PrescriptionDTO prescriptionDTO){
        Prescription prescription = new Prescription();
        prescription.setUserId(prescription.getUserId());
        prescription.setAppointmentId(prescriptionDTO.getAppointmentId());
        prescription.setDoctorId(prescriptionDTO.getDoctorId());
        prescription.setDiagnosis(prescriptionDTO.getDiagnosis());
        prescription.setMedicineList(prescriptionDTO.getMedicineList());
        return prescription;
    }

    public static PrescriptionDTO convertEntityToDTO(Prescription prescription,String emailId){
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setAppointmentId(prescription.getAppointmentId());
        prescriptionDTO.setDiagnosis(prescription.getDiagnosis());
        prescriptionDTO.setDoctorId(prescription.getDoctorId());
        prescriptionDTO.setUserId(prescription.getUserId());
        prescriptionDTO.setMedicineList(prescription.getMedicineList());
        prescriptionDTO.setEmailId(emailId);
        return prescriptionDTO;
    }
}
