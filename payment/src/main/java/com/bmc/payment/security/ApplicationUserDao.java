package com.bmc.payment.security;

public interface ApplicationUserDao {

     ApplicationUser loadUserByUsername(String username);
}
