package com.example.BanHang.repository;

import com.example.BanHang.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {
    Cart findByUser_Id(String id);
    Cart findByUser_UsernameAndStatus(String username, String status);
}
