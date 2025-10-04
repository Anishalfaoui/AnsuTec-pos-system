package com.AnsuTech.AnsuTech.pos.system.service.impl;

import com.AnsuTech.AnsuTech.pos.system.mapper.ProductMapper;
import com.AnsuTech.AnsuTech.pos.system.model.Product;
import com.AnsuTech.AnsuTech.pos.system.model.Store;
import com.AnsuTech.AnsuTech.pos.system.model.User;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.ProductDto;
import com.AnsuTech.AnsuTech.pos.system.repository.ProductRepository;
import com.AnsuTech.AnsuTech.pos.system.repository.StoreRepository;
import com.AnsuTech.AnsuTech.pos.system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(
                productDto.getStoreId()
        ).orElseThrow(
                ()-> new Exception("Store not found")
        );
        Product product = ProductMapper.toEntity(productDto, store);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) {
        return null;
    }

    @Override
    public void deleteProduct(Long id, User user) {

    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        return List.of();
    }
}
