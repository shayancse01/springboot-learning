package com.shayancse.SecurityApp.module6SecurityApplication.dto;

import com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Role;
import lombok.Data;

import java.security.Permission;
import java.util.Set;

@Data
public class SignUpDTO {

    private String email;
    private String password;
    private String name;

    private Set<Role>  roles; //Passing the roles during the signUp process, it's not ideal
    private Set<Permission> permissions;
}
