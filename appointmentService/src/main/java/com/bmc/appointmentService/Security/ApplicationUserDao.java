package com.bmc.appointmentService.Security;

public interface ApplicationUserDao {

     ApplicationUser loadUserByUsername(String username);
}
