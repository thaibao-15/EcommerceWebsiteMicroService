package com.example.BanHang.controller;

import com.example.BanHang.dto.request.ApiResponse;
import com.example.BanHang.dto.request.AuthenticationRequest;
import com.example.BanHang.dto.request.IntrospectRequest;
import com.example.BanHang.dto.request.LogOutRequest;
import com.example.BanHang.dto.response.AuthenticationResponse;
import com.example.BanHang.dto.response.IntrospectResponse;
import com.example.BanHang.service.Authenticationservice;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {
    Authenticationservice authenticationservice;
    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticated(@RequestBody  AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationservice.authenticate(request))
                .build();
    }
    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationservice.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogOutRequest request) throws ParseException, JOSEException {
        authenticationservice.logout(request);
        return ApiResponse.<Void>builder().build();
    }
}
