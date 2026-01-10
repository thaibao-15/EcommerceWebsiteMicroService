package com.example.BanHang.service;

import com.example.BanHang.dto.request.PermissionRequest;
import com.example.BanHang.dto.response.PermissionResponse;
import com.example.BanHang.entity.Permission;
import com.example.BanHang.exception.AppException;
import com.example.BanHang.exception.ErrorCode;
import com.example.BanHang.mapper.PermissionMapper;
import com.example.BanHang.repository.PermisstionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionMapper permissionMapper;
    PermisstionRepository permisstionRepository;
    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse createPermission(PermissionRequest request){
        if(permisstionRepository.existsById(request.getName())){
            throw new AppException(ErrorCode.PERMISSION_EXIST);
        }
        Permission permission = permissionMapper.toPermission(request);
        permisstionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getAllPermissions(){
        return permisstionRepository.findAll()
                .stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePermission(String name){
       permisstionRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXIST));
       permisstionRepository.deleteById(name);
    }
}
