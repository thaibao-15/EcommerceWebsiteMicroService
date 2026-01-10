package com.example.BanHang.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.BanHang.dto.request.CheckoutRequest;
import com.example.BanHang.dto.request.OrderUpdateRequest;
import com.example.BanHang.entity.*;
import com.example.BanHang.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.BanHang.dto.request.OrderCreationRequest;
import com.example.BanHang.dto.response.OrderResponse;
import com.example.BanHang.exception.AppException;
import com.example.BanHang.exception.ErrorCode;
import com.example.BanHang.mapper.OrderMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    UserRepository userRepository;

    CartRepository cartRepository;
    CartItemRepository cartItemRepository;

    OrderDetailRepository orderDetailRepository;


    public OrderResponse updateOrder(OrderUpdateRequest request,Integer orderId){
        Order order =orderRepository.findById(orderId).orElseThrow(
                () -> new AppException(ErrorCode.ORDER_NOT_EXIST));

        orderMapper.updateOrder(order,request);
//        order.setDateUpdate(LocalDateTime.now());
        orderRepository.save(order);

        return orderMapper.toOderResponse(order);
    }

    public OrderResponse createOrderAndOrderDetailFromCart(CheckoutRequest request){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Cart cart =cartRepository.findByUser_Id(user.getId());
        if(cart==null){
            throw  new AppException(ErrorCode.CART_NOT_EXIST);
        }
        BigDecimal total =BigDecimal.ZERO;


        Order order= new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
//      order.setDateUpdate(LocalDateTime.now());
        orderRepository.save(order);




        List<CartItem> selectedItems = cartItemRepository.findAllById(request.getCartItemId());


        for (CartItem item : selectedItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setQuantity(item.getQuantity());
            detail.setProducts(item.getProducts());
            detail.setPrice(item.getProducts().getPrice());
            orderDetailRepository.save(detail);

            BigDecimal totalItem= item.getProducts().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            total= total.add(totalItem);
        }
        order.setTotalAmount(total);
        orderRepository.save(order);


        return orderMapper.toOderResponse(order);

    }

}
