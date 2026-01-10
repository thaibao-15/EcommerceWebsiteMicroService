package com.example.BanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BanHang.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
