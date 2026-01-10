package com.example.BanHang.service;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.CategoryCreationRequest;
import com.example.BanHang.dto.request.CategoryUpdateRequest;
import com.example.BanHang.dto.response.CategoryResponse;
import com.example.BanHang.entity.Categories;
import com.example.BanHang.exception.AppException;
import com.example.BanHang.exception.ErrorCode;
import com.example.BanHang.mapper.CategoryMapper;
import com.example.BanHang.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class CategoryService {
    CategoryMapper categoryMapper;
    CategoryRepository categoryRepository;
    public CategoryResponse createCategory(CategoryCreationRequest request){
        if(categoryRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.CATEGORY_EXIST);
        }

        Categories categories= categoryMapper.toCategory(request);
        categoryRepository.save(categories);

        return categoryMapper.toCategoryResponse(categories);
    }

    public List<CategoryResponse> getCategories(){
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }
    public CategoryResponse getCategory(String id){

        return categoryMapper.toCategoryResponse(categoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_EXIST)));
    }
    public void deleteCategory(String id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXIST);
        }
    }

    public CategoryResponse updateCategory(String id, CategoryUpdateRequest request){
        Categories categories= categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        categoryMapper.toUpdateCategory(categories,request);

        return categoryMapper.toCategoryResponse(categoryRepository.save(categories));
    }

}
