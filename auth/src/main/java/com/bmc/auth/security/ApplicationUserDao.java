package com.bmc.auth.security;

public interface ApplicationUserDao {

     ApplicationUser loadUserByUsername(String username);
}
