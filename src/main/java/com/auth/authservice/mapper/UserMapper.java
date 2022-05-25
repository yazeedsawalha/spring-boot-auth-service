package com.auth.authservice.mapper;

import com.auth.authservice.entity.User;
import com.auth.authservice.web.dto.UserDto;
import org.modelmapper.ModelMapper;

public class UserMapper extends BaseMapper<UserDto, User> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto toDto(User user) {
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto,User.class);
    }
}
