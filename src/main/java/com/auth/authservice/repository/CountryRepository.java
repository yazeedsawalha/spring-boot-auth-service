package com.auth.authservice.repository;

import com.auth.authservice.entity.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends BaseRepository<Country,String> {
}

