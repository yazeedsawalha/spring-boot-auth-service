package com.auth.authservice.web.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CountryDto extends BaseDto{

    private String name;

    private String iso;

    private String iso3;

    private String phoneCode;
}
