package com.auth.authservice.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private T data;
    private Boolean success;
    private String timestamp;
    private String cause;

    public ApiResponse(Boolean success, String cause) {
        this.timestamp = Instant.now().toString();
        this.success = success;
        this.cause = cause;
    }

    public ApiResponse(Boolean success, T data) {
        this.timestamp = Instant.now().toString();
        this.data = data;
        this.success = success;
        this.cause = null;
    }

}
