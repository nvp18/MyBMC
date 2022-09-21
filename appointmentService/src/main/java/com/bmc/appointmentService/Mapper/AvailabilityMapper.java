package com.bmc.appointmentService.Mapper;

import com.bmc.appointmentService.DTO.AvailabilityDTO;
import com.bmc.appointmentService.Entity.Availability;

import java.util.ArrayList;
import java.util.List;

public class AvailabilityMapper {

    public static Availability convertDTOToEntity(AvailabilityDTO availabilityDTO){
        Availability availability = new Availability();
        return availability;
    }
}
