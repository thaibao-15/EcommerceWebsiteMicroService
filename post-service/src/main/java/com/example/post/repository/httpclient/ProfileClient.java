package com.example.post.repository.httpclient;

import com.example.post.dto.ApiResponse;
import com.example.post.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="order-service",url="${app.service.profile.url}")
public interface ProfileClient {
    @GetMapping("/users/myinfo")
    ApiResponse<UserResponse> getMyInfo();
}
