package com.auth.authservice.mapper;

import com.auth.authservice.entity.Country;
import com.auth.authservice.web.dto.CountryDto;
import org.modelmapper.ModelMapper;

public class CountryMapper extends BaseMapper<CountryDto,Country> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CountryDto toDto(Country country) {
        return modelMapper.map(country,CountryDto.class);
    }

    @Override
    public Country toEntity(CountryDto countryDto) {
        return modelMapper.map(countryDto,Country.class);
    }
}
