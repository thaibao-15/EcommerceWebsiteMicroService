package com.example.BanHang.service;

import com.example.BanHang.dto.request.CartCreationRequest;
import com.example.BanHang.dto.response.CartResponse;
import com.example.BanHang.entity.Cart;
import com.example.BanHang.entity.User;
import com.example.BanHang.exception.AppException;
import com.example.BanHang.exception.ErrorCode;
import com.example.BanHang.mapper.CartMapper;
import com.example.BanHang.repository.CartRepository;
import com.example.BanHang.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartService {
    CartMapper cartMapper;
    CartRepository cartRepository;
    UserRepository userRepository;

    public CartResponse createCart(CartCreationRequest request){
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() ->
            new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        Cart cart =cartRepository.findByUser_UsernameAndStatus(name,"active");
        if (cart==null) {

            cart = cartMapper.toCart(request);
            cart.setUser(user);
            cart.setCreatedAt(LocalDateTime.now());
            cart.setStatus("active");

            cartRepository.save(cart);
        }
        return cartMapper.toCartResponse(cart);


    }
}
