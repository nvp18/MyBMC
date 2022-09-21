package com.bmc.payment.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ApplicationUserDaoIMPL implements ApplicationUserDao{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUser loadUserByUsername(String username) {
        return loadAllUser().stream()
                .filter(u->u.getUsername().equalsIgnoreCase(username))
                .findFirst().orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }

    private List<ApplicationUser> loadAllUser(){
        return Arrays.asList(
                ApplicationUser
                        .builder()
                        .username("aditya")
                        .password(passwordEncoder.encode("aditya956"))
                        .authorities(ApplicationRoles.USER.getAuthorities())
                        .build(),
                ApplicationUser
                        .builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .authorities(ApplicationRoles.ADMIN.getAuthorities())
                        .build()
        );
    }
}
