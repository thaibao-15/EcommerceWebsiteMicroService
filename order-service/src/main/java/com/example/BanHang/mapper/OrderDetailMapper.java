package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.OrderDetailCreationRequest;
import com.example.BanHang.dto.response.OrderDetailResponse;
import com.example.BanHang.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetail toOrderDetail(OrderDetailCreationRequest request);

    @Mapping(source = "products.name",target = "productName")
    @Mapping(source = "products.price",target = "priceProduct")
    @Mapping(source = "products.image",target = "image")
    @Mapping(source = "products.status",target = "status")
    OrderDetailResponse toOderDetailResponse(OrderDetail orderDetail);

    List<OrderDetailResponse> toOrderDetailList(List<OrderDetail> response);
}
