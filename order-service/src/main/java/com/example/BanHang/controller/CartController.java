package com.example.BanHang.controller;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.CartCreationRequest;
import com.example.BanHang.dto.response.CartResponse;
import com.example.BanHang.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartController {
    CartService cartService;
    @PostMapping
    ApiResponse<CartResponse> createCart(@RequestBody CartCreationRequest request){
        return ApiResponse.<CartResponse>builder()
                .result(cartService.createCart(request))
                .build();
    }


}
