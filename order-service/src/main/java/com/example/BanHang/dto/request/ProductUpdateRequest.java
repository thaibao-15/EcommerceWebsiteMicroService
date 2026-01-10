package com.example.BanHang.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateRequest {
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private String status;
    private Integer stock;
    private Integer categoryId; // thay vì truyền cả Categories

    private LocalDateTime updatedAt;
}
