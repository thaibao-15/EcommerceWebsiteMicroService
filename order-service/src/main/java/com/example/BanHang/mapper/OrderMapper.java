package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.OrderCreationRequest;
import com.example.BanHang.dto.request.OrderUpdateRequest;
import com.example.BanHang.dto.response.OrderResponse;
import com.example.BanHang.entity.Cart;
import com.example.BanHang.entity.CartItem;
import com.example.BanHang.entity.Order;
import com.example.BanHang.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderCreationRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "status", target = "status")
    OrderResponse toOderResponse(Order order);

    void updateOrder(@MappingTarget Order order, OrderUpdateRequest request);


    @Mapping(target = "id",ignore = true)
    @Mapping(target = "orderDate", ignore = true)
//    @Mapping(target = "dateUpdate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    Order toOrders(Cart cart);



    List<OrderDetail> toOrderDetail(List<CartItem> cartItem);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true) // sẽ set trong service
    @Mapping(target = "price",ignore = true)
    OrderDetail toOrderDetail(CartItem cartItem);
    
}
