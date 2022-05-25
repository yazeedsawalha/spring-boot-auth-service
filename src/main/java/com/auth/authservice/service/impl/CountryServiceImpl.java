package com.auth.authservice.service.impl;

import com.auth.authservice.entity.Country;
import com.auth.authservice.mapper.CountryMapper;
import com.auth.authservice.repository.CountryRepository;
import com.auth.authservice.service.AbstractService;
import com.auth.authservice.service.CountryService;
import com.auth.authservice.web.dto.CountryDto;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends AbstractService<CountryDto, Country> implements CountryService {
    public CountryServiceImpl(CountryRepository countryRepository) {
        super(countryRepository, new CountryMapper(), Country.class);
    }
}
