package com.AnsuTech.AnsuTech.pos.system.payload.dto;

import com.AnsuTech.AnsuTech.pos.system.model.Store;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;
   // private Store store;
    private Long storeId;
}
