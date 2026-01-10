package com.example.BanHang.controller;

import java.util.List;

import com.example.BanHang.dto.request.ApiResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.BanHang.dto.request.CartItemCreationRequest;
import com.example.BanHang.dto.request.CartItemUpdateRequest;
import com.example.BanHang.dto.request.DeleteListCartItemsRequest;
import com.example.BanHang.dto.response.CartItemResponse;
import com.example.BanHang.service.CartItemService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartItemController {
    CartItemService cartItemService;
    @PostMapping
    ApiResponse<CartItemResponse> createCartItem(@RequestBody CartItemCreationRequest request){
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.createCartItem(request))
                .build();
    }
    @GetMapping()
    ApiResponse<List<CartItemResponse>> getAllCartItems(){
        return ApiResponse.<List<CartItemResponse>>builder()
                .result(cartItemService.getAllCartItems())
                .build();
    }
//    @DeleteMapping
//    ApiResponse<String> deleteCartItemById(@RequestBody DeleteCartItemRequest request){
//        cartItemService.deleteCartItem(request);
//        return ApiResponse.<String>builder()
//                .result("Cart item has been deleted")
//                .build();
//    }
    @DeleteMapping
    ApiResponse<String> deletCartItemsByListId(@RequestBody DeleteListCartItemsRequest request){
        cartItemService.deleleteCartItemByListId(request.getId());
        return ApiResponse.<String>builder()
                .result("Cart item has been deleted")
                .build();
    }
    @PutMapping
    ApiResponse<CartItemResponse> updateCartItem(@RequestBody CartItemUpdateRequest request){
        return ApiResponse.<CartItemResponse>builder()
                .result(cartItemService.updateCartItem(request))
                .build();
    }
}
