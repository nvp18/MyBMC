package com.bmc.appointmentService.DTO;

import com.bmc.appointmentService.common.Medicine;

import java.util.ArrayList;

public class PrescriptionDTO {

    private String appointmentId;
    private String doctorId;
    private String userId;
    private String diagnosis;
    private ArrayList<Medicine> medicineList;
    private String emailId;



    public PrescriptionDTO() {
    }

    public PrescriptionDTO(String appointmentId, String doctorId, String userId, String diagnosis, ArrayList<Medicine> medicineList, String emailId) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.diagnosis = diagnosis;
        this.medicineList = medicineList;
        this.emailId=emailId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public ArrayList<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }
}
