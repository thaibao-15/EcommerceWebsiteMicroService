package com.example.BanHang.dto.response;

import com.example.BanHang.entity.Order;
import com.example.BanHang.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    private Integer quantity;

    private double price;

//    Order order;

    String productName;
    double priceProduct;
    String image;
    String status;

}
