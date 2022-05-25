package com.auth.authservice.web.controller;

import com.auth.authservice.service.CountryService;
import com.auth.authservice.web.dto.CountryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryController extends BaseController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@RequestBody CountryDto countryDto) {
        return doCall(() -> countryService.save(countryDto));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return doCall(countryService::findAll);
    }

}
