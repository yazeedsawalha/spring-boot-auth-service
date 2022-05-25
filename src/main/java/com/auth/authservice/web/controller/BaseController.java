package com.auth.authservice.web.controller;

import com.auth.authservice.web.common.ApiCall;
import com.auth.authservice.web.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public ResponseEntity<?> doCall(ApiCall apiCall) {
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, apiCall.call()));
    }
}
