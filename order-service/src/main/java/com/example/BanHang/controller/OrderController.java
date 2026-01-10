package com.example.BanHang.controller;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.OrderCreationRequest;
import com.example.BanHang.dto.request.OrderUpdateRequest;
import com.example.BanHang.dto.response.OrderResponse;
import com.example.BanHang.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderController {
    OrderService orderService;

    @PutMapping("/{id}")
    ApiResponse<OrderResponse> updateOrder(@RequestBody OrderUpdateRequest request,@PathVariable Integer id){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateOrder(request,id))
                .build();
    }
}
