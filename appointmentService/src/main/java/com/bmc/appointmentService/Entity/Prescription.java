package com.bmc.appointmentService.Entity;

import com.bmc.appointmentService.common.Medicine;

import javax.persistence.Id;
import java.util.ArrayList;

public class Prescription {

    @Id
    private String id;
    private String userId;

    private String doctorId;

    private String doctorName;

    private String appointmentId;

    private String diagnosis;

    private ArrayList<Medicine> medicineList;

    public Prescription(String id,String userId, String doctorId, String doctorName, String appointmentId, String diagnosis, ArrayList<Medicine> medicineList) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.medicineList = medicineList;
    }

    public Prescription() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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
