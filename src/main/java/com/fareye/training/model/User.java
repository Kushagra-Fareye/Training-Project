package com.fareye.training.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

enum Role{
    USER,
    ADMIN
}

@Getter @Setter
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean verified;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String password;
    private Role role;
    private Boolean active;
    
}