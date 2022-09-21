package com.bmc.ratingservice.security;

public interface ApplicationUserDao {

     ApplicationUser loadUserByUsername(String username);
}
