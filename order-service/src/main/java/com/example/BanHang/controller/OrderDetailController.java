package com.example.BanHang.controller;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.OrderDetailCreationRequest;
import com.example.BanHang.dto.response.OrderDetailResponse;
import com.example.BanHang.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderdetail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDetailController {
    OrderDetailService orderDetailService;
    @PostMapping
    ApiResponse<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailCreationRequest request){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.createOrderDetail(request))
                .build();
    }
    @GetMapping("/{id}")
    ApiResponse<List<OrderDetailResponse>> getAllOrderDetailById(@PathVariable Integer id){
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getAllOrderDetailById(id))
                .build();
    }
}
