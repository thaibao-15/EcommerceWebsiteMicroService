package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.ProductCreationRequest;
import com.example.BanHang.dto.request.ProductUpdateRequest;
import com.example.BanHang.dto.response.ProductResponse;
import com.example.BanHang.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest request);
    ProductResponse toProductResponse(Product product);
    @Mapping(target = "categories",ignore = true)
    void toUpdateProduct(@MappingTarget Product product, ProductUpdateRequest request);

}
