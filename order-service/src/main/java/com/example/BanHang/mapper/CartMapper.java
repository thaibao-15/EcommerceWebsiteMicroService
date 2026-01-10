package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.CartCreationRequest;
import com.example.BanHang.dto.response.CartResponse;
import com.example.BanHang.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CartCreationRequest request);
    @Mapping(source = "user.id", target = "userId")
    CartResponse toCartResponse(Cart cart);
}
