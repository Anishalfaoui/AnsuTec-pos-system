package com.AnsuTech.AnsuTech.pos.system.payload.response;

import com.AnsuTech.AnsuTech.pos.system.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto user;
}
