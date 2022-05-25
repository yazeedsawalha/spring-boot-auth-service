package com.auth.authservice.web.controller;

import com.auth.authservice.service.AuthService;
import com.auth.authservice.service.UserService;
import com.auth.authservice.web.dto.LoginDto;
import com.auth.authservice.web.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    private final UserService userService;

    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserDto registerUserDto) {
        return doCall(() -> userService.save(registerUserDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        return doCall(() -> authService.signin(loginDto.getLogin(), loginDto.getPassword()));
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<?> whoami(HttpServletRequest req) {
        return doCall(() -> authService.whoami(req));
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<?> refresh(HttpServletRequest req) {
        return doCall(() -> authService.refresh(req.getRemoteUser()));
    }

}
