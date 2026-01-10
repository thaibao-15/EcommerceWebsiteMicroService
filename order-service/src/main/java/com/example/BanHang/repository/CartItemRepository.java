package com.example.BanHang.repository;

import java.util.List;
import java.util.Optional;

import com.example.BanHang.dto.request.CheckoutRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BanHang.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,String> {

    List<CartItem> findByCart_Id(Integer cartId);


    Optional<CartItem> findByCart_IdAndProducts_Id(Integer cartId,Integer productId);

}
