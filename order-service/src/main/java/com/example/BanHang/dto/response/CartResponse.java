package com.example.BanHang.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String userId; // nếu sau này bạn có entity User thì nên đổi sang @ManyToOne


    LocalDateTime createdAt;

    // Quan hệ 1-nhiều với CartItem
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItemResponse> items;
}
