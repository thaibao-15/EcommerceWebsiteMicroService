package com.example.BanHang.mapper;

import com.example.BanHang.dto.request.RoleRequest;
import com.example.BanHang.dto.response.RoleResponse;
import com.example.BanHang.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
