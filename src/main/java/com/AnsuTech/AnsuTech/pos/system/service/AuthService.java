package com.AnsuTech.AnsuTech.pos.system.service;

import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.UserDto;
import com.AnsuTech.AnsuTech.pos.system.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse Login(UserDto userDto) throws UserException;

}
