package com.example.BanHang.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    Integer id;
    String userId;
    String username;

    Long cartId;

    Integer productId;   // tham chiếu sản phẩm

    String productName;

    String image;

    private BigDecimal price;

    Integer quantity;
    Integer stock;
    String description;
}
