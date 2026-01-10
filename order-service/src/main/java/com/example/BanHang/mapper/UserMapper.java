package com.example.BanHang.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.BanHang.dto.request.UserCreationRequest;
import com.example.BanHang.dto.request.UserUpdateRequest;
import com.example.BanHang.dto.response.UserResponse;
import com.example.BanHang.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel ="spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
