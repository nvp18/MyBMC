package com.bmc.doctorService.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRoles {
    USER(Sets.newHashSet(ApplicationPermission.PAYMENT)),
    ADMIN;

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
