package com.AnsuTech.AnsuTech.pos.system.service.impl;

import com.AnsuTech.AnsuTech.pos.system.configuration.JwtProvided;
import com.AnsuTech.AnsuTech.pos.system.domain.UserRole;
import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.mapper.UserMapper;
import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.UserDto;
import com.AnsuTech.AnsuTech.pos.system.payload.response.AuthResponse;
import com.AnsuTech.AnsuTech.pos.system.repository.UserRepository;
import com.AnsuTech.AnsuTech.pos.system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl  implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvided  jwtProvided;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new UserException("email id already registered");
        }
        if (userDto.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new UserException("role admin is not allowed");
        }

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());
        newUser.setLastLoginAt(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvided.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered successfully");
        authResponse.setUser(UserMapper.toDTO(savedUser));
        return authResponse;
    }

    @Override
    public AuthResponse Login(UserDto userDto) throws UserException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtProvided.generateToken(authentication);

        User user =  userRepository.findByEmail(email);

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("login successfully");
        authResponse.setUser(UserMapper.toDTO(user));
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {

        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);

        if (userDetails == null) {
            throw new UserException("Invalid email "+ email);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }
}
