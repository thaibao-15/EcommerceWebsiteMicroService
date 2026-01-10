package com.example.BanHang.dto.request;

import com.example.BanHang.entity.Categories;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
    private String status;
    private Integer stock;
    private Integer categoryId; // thay vì truyền cả Categories
}
