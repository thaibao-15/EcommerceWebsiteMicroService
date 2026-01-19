package com.example.ChatService.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ChatService.dto.ApiResponse;
import com.example.ChatService.dto.response.UserProfileResponse;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "identity-client", url = "${app.services.profile.url}")
public interface ProfileClient {
    @GetMapping("/users/myinfo")
    ApiResponse<UserProfileResponse> getMyInfo();
    @GetMapping("/users/{id}")
    ApiResponse<UserProfileResponse> getProfileById(@PathVariable("id") String id);
}
