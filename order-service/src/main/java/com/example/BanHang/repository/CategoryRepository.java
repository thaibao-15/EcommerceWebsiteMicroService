package com.example.BanHang.repository;

import com.example.BanHang.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, String> {
    boolean existsByName(String name);
}
