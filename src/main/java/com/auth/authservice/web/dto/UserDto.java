package com.auth.authservice.web.dto;

import com.auth.authservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private Date birthDate;

    private Boolean emailVerified;

    private Boolean active;

    private String image;

    private String country;

    private Set<Role> roles;
}
