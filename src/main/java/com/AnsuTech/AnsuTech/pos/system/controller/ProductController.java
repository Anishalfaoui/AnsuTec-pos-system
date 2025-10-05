package com.AnsuTech.AnsuTech.pos.system.controller;


import com.AnsuTech.AnsuTech.pos.system.exceptions.UserException;
import com.AnsuTech.AnsuTech.pos.system.payload.dto.ProductDto;
import com.AnsuTech.AnsuTech.pos.system.payload.response.ApiResponse;
import com.AnsuTech.AnsuTech.pos.system.service.ProductService;
import com.AnsuTech.AnsuTech.pos.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(
                productService.createProduct(productDto, userService.getUserFromJwtToken(jwt))
        );

    }


    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getByStoreId(@PathVariable Long storeId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(
                productService.getProductsByStoreId(storeId)
        );

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id,
                                             @RequestBody ProductDto productDto,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(
                productService.updateProduct(id,productDto, userService.getUserFromJwtToken(jwt))
        );

    }


    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDto>> searchByKeyword(@PathVariable Long storeId,
                                                         @RequestParam String keyword,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(
                productService.searchByKeyword(
                        storeId,
                        keyword
                )
        );

    }




    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id,
                                              @RequestHeader("Authorization") String jwt) throws Exception {
        productService.deleteProduct(id, userService.getUserFromJwtToken(jwt));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        return ResponseEntity.ok(
                apiResponse

        );

    }
}
