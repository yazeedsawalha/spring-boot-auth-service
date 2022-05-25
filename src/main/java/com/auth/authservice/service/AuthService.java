package com.auth.authservice.service;

import com.auth.authservice.web.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    String signin(String username, String password);

    UserDto whoami(HttpServletRequest req);

    String refresh(String username);
}
