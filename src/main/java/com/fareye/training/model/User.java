package com.fareye.training.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

enum Role {
    USER,
    ADMIN
}

@Getter
@Setter
public class User {
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    private Boolean verified;
    private LocalDateTime created;
    private LocalDateTime modified;
    @NotNull
    private String password;
    private String name;
    private Role role;
    private Boolean active;
    private Integer age;
    private String bloodGroup;
    private String company;
    private String phoneNumber;
}