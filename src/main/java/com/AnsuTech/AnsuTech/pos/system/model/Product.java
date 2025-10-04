package com.AnsuTech.AnsuTech.pos.system.model;

import com.AnsuTech.AnsuTech.pos.system.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    private  String description;
    @Column(nullable = false)
    private   String sku;
    private    Double mrp;
    private   Double sellingPrice;
    private   String brand;
    private    String image;
    //private Category category;

    @ManyToOne
    private   Store store;

    private   LocalDateTime createdAt;
    private    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
