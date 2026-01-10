package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.PermissionRequest;
import com.example.BanHang.dto.response.PermissionResponse;
import com.example.BanHang.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
