package com.auth.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseEntity {

    private String name;

    @Indexed(unique = true, name = "iso")
    private String iso;

    @Indexed(unique = true, name = "iso3")
    private String iso3;

    @Indexed(unique = true, name = "phoneCode")
    private String phoneCode;
}