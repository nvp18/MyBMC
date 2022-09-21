package com.bmc.appointmentService.common;

public class Medicine {

    private String name;

    private String dosage;

    private String frequency;

    private String remarks;

    public Medicine(String name, String dosage, String frequency, String remarks) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.remarks = remarks;
    }

    public Medicine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
