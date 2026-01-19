package com.example.ChatService.controller;

import com.example.ChatService.dto.ApiResponse;
import com.example.ChatService.dto.request.ConversationRequest;
import com.example.ChatService.dto.response.ConversationResponse;
import com.example.ChatService.service.ConversationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversationController {
    ConversationService conversationService;
    @PostMapping("/create")
    public ApiResponse<ConversationResponse> createConversation(@RequestBody ConversationRequest request){
        return ApiResponse.<ConversationResponse>builder()
                .result(conversationService.create(request))
                .build();
    }
}
