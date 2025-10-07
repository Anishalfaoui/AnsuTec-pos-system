package com.AnsuTech.AnsuTech.pos.system.service;

import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto) throws Exception;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id,CategoryDto dto) throws Exception;
    void deleteCategory(Long id) throws Exception;
}
