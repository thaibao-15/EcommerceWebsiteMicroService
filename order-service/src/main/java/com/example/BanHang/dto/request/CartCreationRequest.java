package com.example.BanHang.dto.request;

import com.example.BanHang.entity.CartItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCreationRequest {
//    String userId; // nếu sau này bạn có entity User thì nên đổi sang @ManyToOne

//    LocalDateTime createdAt;

    // Quan hệ 1-nhiều với CartItem
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItem> items;
}
