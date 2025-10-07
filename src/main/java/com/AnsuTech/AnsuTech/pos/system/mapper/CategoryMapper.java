package com.AnsuTech.AnsuTech.pos.system.mapper;

import com.AnsuTech.AnsuTech.pos.system.model.Category;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)
                .build();
    }
}
