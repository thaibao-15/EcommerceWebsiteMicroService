package com.example.ChatService.controller;

import com.example.ChatService.dto.ApiResponse;
import com.example.ChatService.dto.request.ChatMessageRequest;
import com.example.ChatService.dto.response.ChatMessageResponse;
import com.example.ChatService.service.ChatMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ChatMessageController {
    ChatMessageService chatMessageService;
    @PostMapping("/create")
    public ApiResponse<ChatMessageResponse> createChat(@RequestBody ChatMessageRequest request){
        return ApiResponse.<ChatMessageResponse>builder()
                .result(chatMessageService.createChat(request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<ChatMessageResponse>> getChat(@RequestParam("conversationId") String conversationId){
        return ApiResponse.<List<ChatMessageResponse>>builder()
                .result(chatMessageService.getMessage(conversationId))
                .build();
    }

}
