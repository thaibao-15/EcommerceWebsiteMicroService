package com.example.BanHang.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "category")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(length = 500)
    private String description;

    // Quan hệ 1-n với Product
    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude  // tránh vòng lặp khi in ra
    private List<Product> products;
}
