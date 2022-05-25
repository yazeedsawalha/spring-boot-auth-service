package com.auth.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    @Indexed(unique = true,name = "username")
    private String username;

    @Indexed(unique = true,name = "username")
    private String email;

    private String password;

    private Date birthDate;

    private Boolean emailVerified = Boolean.FALSE;

    private Boolean active = Boolean.TRUE;

    private String image;

    private String country;

    private Set<Role> roles;

}
