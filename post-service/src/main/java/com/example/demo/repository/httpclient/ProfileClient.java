package com.example.demo.repository.httpclient;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.response.UserProfileResponse;
import com.example.demo.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="order-service",url="${app.service.profile.url}")
public interface ProfileClient {
    @GetMapping("/myinfo")
    ApiResponse<UserResponse> getMyInfo();
}
