package com.example.BanHang.service;

import com.example.BanHang.dto.request.ProductCreationRequest;
import com.example.BanHang.dto.request.ProductUpdateRequest;
import com.example.BanHang.dto.response.ProductResponse;
import com.example.BanHang.entity.Categories;
import com.example.BanHang.entity.Product;
import com.example.BanHang.exception.AppException;
import com.example.BanHang.exception.ErrorCode;
import com.example.BanHang.mapper.ProductMapper;
import com.example.BanHang.repository.CategoryRepository;
import com.example.BanHang.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {
    ProductMapper productMapper;
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductCreationRequest request){
        Categories categories=  categoryRepository.findById(String.valueOf(request.getCategoryId()))
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));

        Product product = productMapper.toProduct(request);
        product.setCategories(categories);
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }
    public List<ProductResponse> getAllProducts(){
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toProductResponse).toList();
    }
    public ProductResponse getProduct(String id){
        return productMapper.toProductResponse(productRepository
                .findById(Integer.valueOf(id))
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXIST)));
    }
    public void deleteProduct(String id){
        productRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXIST));
        productRepository.deleteById(Integer.valueOf(id));
    }
    public ProductResponse updateProduct(String id, ProductUpdateRequest request){
        Product product =productRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXIST));
        productMapper.toUpdateProduct(product,request);
        // tự động set thời gian update
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }
}
