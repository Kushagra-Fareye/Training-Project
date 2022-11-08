package com.fareye.training.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

enum Role {
    USER,
    ADMIN
}

@Getter
@Setter
@ToString
@Entity
@Repository
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    private LocalDateTime created;
    @NotNull
    private String password;
    private String name;
    private Integer age;
    private String bloodGroup;
    private String phoneNumber;
    private String avatar_url;
    @Column(unique = true)
    @NotNull
    private String userName;

}