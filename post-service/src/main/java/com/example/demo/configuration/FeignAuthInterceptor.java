package com.example.demo.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

public class FeignAuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        requestTemplate.header("Authorization","Bearer " + token);
        // tự động thêm bearer token vào feign client
    }
}
