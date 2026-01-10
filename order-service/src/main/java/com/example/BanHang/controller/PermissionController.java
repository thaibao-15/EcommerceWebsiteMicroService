package com.example.BanHang.controller;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.PermissionRequest;
import com.example.BanHang.dto.response.PermissionResponse;
import com.example.BanHang.service.PermissionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;
    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody @Valid PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPermissions(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAllPermissions())
                .build();
    }
    @DeleteMapping("/{id}")
    ApiResponse<String> deletePermission(@PathVariable String id){
        permissionService.deletePermission(id);
        return ApiResponse.<String>builder()
                .result("Permission has been deleted!")
                .build();
    }
}
