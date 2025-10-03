package com.AnsuTech.AnsuTech.pos.system.controller;

import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.UserDto;
import com.AnsuTech.AnsuTech.pos.system.payload.response.AuthResponse;
import com.AnsuTech.AnsuTech.pos.system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody UserDto userDto
    ) throws UserException {
        return ResponseEntity.ok(authService.signup(userDto));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody UserDto userDto
    ) throws UserException {
        return ResponseEntity.ok(authService.Login(userDto));
    }
}
