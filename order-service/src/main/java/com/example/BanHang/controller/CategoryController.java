package com.example.BanHang.controller;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.CategoryCreationRequest;
import com.example.BanHang.dto.request.CategoryUpdateRequest;
import com.example.BanHang.dto.response.CategoryResponse;
import com.example.BanHang.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryCreationRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAllCategories(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getCategories())
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable String id){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getCategory(id))
                .build();
    }
    @DeleteMapping("/{id}")
    ApiResponse<String> deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
        return ApiResponse.<String>builder()
                .result("Category deleted successfully")
                .build();
    }
    @PutMapping("/{id}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable String id,@RequestBody CategoryUpdateRequest request){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(id,request))
                .build();
    }
}
