package com.fareye.training.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

enum Role {
    USER,
    ADMIN
}

@Getter
@Setter
@ToString
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
    private String avatar_url;
    private String userName;
}