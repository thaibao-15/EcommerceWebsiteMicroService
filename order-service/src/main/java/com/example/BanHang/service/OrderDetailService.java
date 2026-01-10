package com.example.BanHang.service;

import com.example.BanHang.dto.request.OrderDetailCreationRequest;
import com.example.BanHang.dto.response.OrderDetailResponse;
import com.example.BanHang.entity.Order;
import com.example.BanHang.entity.OrderDetail;
import com.example.BanHang.entity.Product;
import com.example.BanHang.exception.AppException;
import com.example.BanHang.exception.ErrorCode;
import com.example.BanHang.mapper.OrderDetailMapper;
import com.example.BanHang.repository.OrderDetailRepository;
import com.example.BanHang.repository.OrderRepository;
import com.example.BanHang.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDetailService {
    OrderDetailMapper orderDetailMapper;
    OrderDetailRepository orderDetailRepository;

    OrderRepository orderRepository;

    ProductRepository productRepository;

    public OrderDetailResponse createOrderDetail(OrderDetailCreationRequest request){

        Product product =productRepository.findById(request.getProductId()).orElseThrow(
                ()->new AppException(ErrorCode.PRODUCT_NOT_EXIST));

        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(
                ()->new AppException(ErrorCode.ORDER_NOT_EXIST));

        OrderDetail orderDetail =orderDetailMapper.toOrderDetail(request);
        orderDetail.setOrder(order);
        orderDetail.setProducts(product);

        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOderDetailResponse(orderDetail);
    }

    public List<OrderDetailResponse> getAllOrderDetailById(Integer orderId){
        Order order =orderRepository.findById(orderId).orElseThrow(
                ()->new AppException(ErrorCode.ORDER_NOT_EXIST));
        List<OrderDetail> list =orderDetailRepository.findByOrder_Id(orderId);

        return orderDetailMapper.toOrderDetailList(list);
    }
}
