package com.example.BanHang.dto.request;

import com.example.BanHang.entity.Order;
import com.example.BanHang.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreationRequest {
    private Integer quantity;

    private double price;

    int orderId;

    int productId;

}
