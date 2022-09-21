package com.bmc.payment.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.bmc.payment.security.ApplicationPermission.*;


public enum ApplicationRoles {
    USER(Sets.newHashSet(PAYMENT,DOCTOR_AVAILABILITY_UPDATE,DOCTOR_AVAILABILITY,
            BOOK_APPOINTMENT,APPOINTMENT_DETAILS,USER_APPOINTMENTS,SEND_PRESCRIPTION,
            DOCTOR_INFO,UPLOAD_DOCTOR_DOCUMENTS,TOP_20_DOCTOR_INFO,DOCTOR_INFO_ON_ID,
            POST_USER_INFO,GET_USER_INFO,RATING)),
    ADMIN(Sets.newHashSet(DOCTOR_AVAILABILITY_UPDATE,DOCTOR_AVAILABILITY,
            BOOK_APPOINTMENT,APPOINTMENT_DETAILS,USER_APPOINTMENTS,
            DOCTOR_INFO,UPLOAD_DOCTOR_DOCUMENTS,APPROVE_REGISTRATION,REJECT_REGISTRATION
            ,TOP_20_DOCTOR_INFO,DOCTOR_INFO_ON_ID,GET_USER_INFO));

    private Set<ApplicationPermission> permission;

    ApplicationRoles(Set<ApplicationPermission> applicationPermissions){
        this.permission = applicationPermissions;
    }

    ApplicationRoles(){

    }

    public Set<ApplicationPermission> getPermission(){
        return this.permission;
    }

    public Set<GrantedAuthority> getAuthorities(){
        return this.permission.stream().map(p-> new SimpleGrantedAuthority(p.name())).collect(Collectors.toSet());
    }
}
