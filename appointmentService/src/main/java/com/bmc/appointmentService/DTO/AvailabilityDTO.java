package com.bmc.appointmentService.DTO;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AvailabilityDTO {

    private String doctorId;
    private LinkedHashMap<Date,List<String>> availabilityMap;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(String doctorId, LinkedHashMap<Date,List<String>> availabilityMap) {
        this.doctorId = doctorId;
        this.availabilityMap = availabilityMap;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LinkedHashMap<Date,List<String>> getAvailabilityMap() {
        return availabilityMap;
    }

    public void setAvailabilityMap(LinkedHashMap<Date,List<String>> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }
}
