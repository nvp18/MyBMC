package com.bmc.doctorService.Security;

public interface ApplicationUserDao {

     ApplicationUser loadUserByUsername(String username);
}
