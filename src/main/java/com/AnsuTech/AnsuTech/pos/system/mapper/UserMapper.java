package com.AnsuTech.AnsuTech.pos.system.mapper;

import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.UserDto;

public class UserMapper {
    public static UserDto toDTO(User savedUser) {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setEmail(savedUser.getEmail());
        userDto.setFullName(savedUser.getFullName());
        userDto.setRole(savedUser.getRole());
        userDto.setPhone(savedUser.getPhone());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLoginAt(savedUser.getLastLoginAt());
        return userDto;
    }
}
