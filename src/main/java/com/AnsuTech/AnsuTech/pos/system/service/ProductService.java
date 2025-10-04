package com.AnsuTech.AnsuTech.pos.system.service;

import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    ProductDto updateProduct(Long id,ProductDto productDto, User user);
    void deleteProduct(Long id, User user);
    List<ProductDto> getProductsByStoreId(Long storeId);
    List<ProductDto> searchByKeyword(Long storeId ,String keyword);
}
