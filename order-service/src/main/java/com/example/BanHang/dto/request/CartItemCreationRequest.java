package com.example.BanHang.dto.request;

import com.example.BanHang.entity.Cart;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemCreationRequest {

    Integer cartId;

    Integer productId; // nếu bạn có entity Product thì nên đổi sang @ManyToOne

    Integer quantity;
}
