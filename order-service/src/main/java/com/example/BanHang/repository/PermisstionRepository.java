package com.example.BanHang.repository;

import com.example.BanHang.dto.request.PermissionRequest;
import com.example.BanHang.dto.response.PermissionResponse;
import com.example.BanHang.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisstionRepository extends JpaRepository<Permission,String> {

}
