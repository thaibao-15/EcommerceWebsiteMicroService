package com.example.ApiGateway.repository;

import com.example.ApiGateway.dto.ApiResponse;
import com.example.ApiGateway.dto.request.IntrospectRequest;
import com.example.ApiGateway.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentifyClient {
@PostExchange(url = "/auth/introspect",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);
}
