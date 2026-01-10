package com.example.BanHang.controller;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.ProductCreationRequest;
import com.example.BanHang.dto.request.ProductUpdateRequest;
import com.example.BanHang.dto.response.ProductResponse;
import com.example.BanHang.service.ProductService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {
    ProductService productService;
    @PostMapping
    ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts(){
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }
    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getProduct(@PathVariable String id){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProduct(id))
                .build();
    }
    @DeleteMapping("/{id}")
    ApiResponse<String> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ApiResponse.<String>builder()
                .result("Product Has been Deleted!")
                .build();
    }
    @PutMapping("/{id}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest request){
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(id,request))
                .build();
    }

}
