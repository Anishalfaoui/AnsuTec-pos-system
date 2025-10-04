package com.AnsuTech.AnsuTech.pos.system.payload.dto;

import com.AnsuTech.AnsuTech.pos.system.model.Store;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {
    private  Long id;

    private String name;
    private String description;
    private String sku;
    private  Double mrp;
    private Double sellingPrice;
    private String brand;
    private String image;
    private Long categoryId;

    private Long storeId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
