package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.CategoryCreationRequest;
import com.example.BanHang.dto.request.CategoryUpdateRequest;
import com.example.BanHang.dto.response.CategoryResponse;
import com.example.BanHang.entity.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface CategoryMapper {
    Categories toCategory(CategoryCreationRequest request);
    CategoryResponse toCategoryResponse(Categories categories);
    @Mapping(target = "products",ignore = true)
    void toUpdateCategory(@MappingTarget Categories categories, CategoryUpdateRequest request);
}
