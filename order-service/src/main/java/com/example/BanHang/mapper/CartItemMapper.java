package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.CartItemCreationRequest;
import com.example.BanHang.dto.request.CartItemUpdateRequest;
import com.example.BanHang.dto.response.CartItemResponse;
import com.example.BanHang.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "cartId", target = "cart.id")
    @Mapping(source = "productId", target = "products.id")
    CartItem toCartItem(CartItemCreationRequest request);


    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "products.id",target = "productId")
    @Mapping(source = "products.name", target = "productName")
    @Mapping(source = "products.image",target = "image")
    @Mapping(source = "products.price",target = "price")
    @Mapping(source = "products.stock",target = "stock")
    @Mapping(source = "products.description",target = "description")
    @Mapping(source = "cart.user.id",target = "userId")
    @Mapping(source = "cart.user.username",target = "username")
    CartItemResponse toCartItemResponse(CartItem cartItem);


    List<CartItemResponse> toCartItemList(List<CartItem> response);

    void updateCartItem(@MappingTarget CartItem cartItem, CartItemUpdateRequest request);

}
