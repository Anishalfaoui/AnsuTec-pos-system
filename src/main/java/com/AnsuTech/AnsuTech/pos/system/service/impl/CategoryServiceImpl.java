package com.AnsuTech.AnsuTech.pos.system.service.impl;

import com.AnsuTech.AnsuTech.pos.system.domain.UserRole;
import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.mapper.CategoryMapper;
import com.AnsuTech.AnsuTech.pos.system.model.Category;
import com.AnsuTech.AnsuTech.pos.system.model.Store;
import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.CategoryDto;
import com.AnsuTech.AnsuTech.pos.system.repository.CategoryRepository;
import com.AnsuTech.AnsuTech.pos.system.repository.StoreRepository;
import com.AnsuTech.AnsuTech.pos.system.service.CategoryService;
import com.AnsuTech.AnsuTech.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto dto) throws Exception {
        User user = userService.getCurrentUser();
        Store store= storeRepository.findById(dto.getStoreId()).orElseThrow(
                () -> new UserException("Store with id: " + dto.getStoreId() + " not found")
        );

        Category category = Category.builder()
                .store(store)
                .name(dto.getName())
                .build();
        checkAuthority(user, category.getStore());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        List<CategoryDto> categoriesDto = new ArrayList<>();
        for(Category category : categories){
            categoriesDto.add(CategoryMapper.toDto(category));
        }
        return categoriesDto;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("category not found")
        );
        User user = userService.getCurrentUser();
        category.setName(dto.getName());
        checkAuthority(user, category.getStore());
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("category not found")
        );
        User user = userService.getCurrentUser();
        checkAuthority(user, category.getStore());
        categoryRepository.deleteById(id);

    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if (!(isAdmin && isSameStore) && !isManager){
            throw new Exception("you dont have permission to manage this category");

        }
    }
}
