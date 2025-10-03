package com.AnsuTech.AnsuTech.pos.system.payload.dto;

import com.AnsuTech.AnsuTech.pos.system.domain.StoreStatus;
import com.AnsuTech.AnsuTech.pos.system.model.StoreContact;
import com.AnsuTech.AnsuTech.pos.system.model.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StoreDto {
    private Long id;

    private String brand;

    private UserDto storeAdmin;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;
}
