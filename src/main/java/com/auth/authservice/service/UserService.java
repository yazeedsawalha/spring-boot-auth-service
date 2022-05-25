package com.auth.authservice.service;

import com.auth.authservice.web.dto.UserDto;

public interface UserService extends BasicOperation<UserDto> {
    UserDto getByUsername(String username);
}
