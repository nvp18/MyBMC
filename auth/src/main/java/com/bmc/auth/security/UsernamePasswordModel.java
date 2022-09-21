package com.bmc.auth.security;

import lombok.Data;

@Data
public class UsernamePasswordModel {

    private String userName;
    private String password;
}
