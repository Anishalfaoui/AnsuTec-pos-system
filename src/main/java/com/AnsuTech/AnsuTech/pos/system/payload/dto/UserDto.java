package com.AnsuTech.AnsuTech.pos.system.payload.dto;

import com.AnsuTech.AnsuTech.pos.system.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private UserRole role;

    private String password;

    private LocalDateTime lastLoginAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
