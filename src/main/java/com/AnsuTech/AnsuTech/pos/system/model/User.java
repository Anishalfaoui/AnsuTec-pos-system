package com.AnsuTech.AnsuTech.pos.system.model;


import com.AnsuTech.AnsuTech.pos.system.domain.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    private Long id;

    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    @Column(nullable = false)
    private UserRole role;

    private String password;

    private LocalDateTime lastLoginAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;



}
