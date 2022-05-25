package com.auth.authservice.service.impl;

import com.auth.authservice.entity.User;
import com.auth.authservice.mapper.UserMapper;
import com.auth.authservice.repository.UserRepository;
import com.auth.authservice.service.AbstractService;
import com.auth.authservice.service.UserService;
import com.auth.authservice.web.dto.RegisterUserDto;
import com.auth.authservice.web.dto.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<UserDto, User> implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        super(userRepository, new UserMapper(), User.class);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save(UserDto userDto) {
        if(userDto instanceof RegisterUserDto){
            RegisterUserDto registerUserDto = (RegisterUserDto) userDto;
            if(StringUtils.isEmpty(registerUserDto.getId())){
                registerUserDto.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
            }
        }
        return super.save(userDto);
    }

    @Override
    public UserDto getByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("username can not be null or empty");
        }
        return new UserMapper().toDto(userRepository.findByUsername(username));
    }
}
