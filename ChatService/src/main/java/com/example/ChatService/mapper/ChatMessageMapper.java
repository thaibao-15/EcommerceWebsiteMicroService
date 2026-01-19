package com.example.ChatService.mapper;

import com.example.ChatService.dto.response.ChatMessageResponse;
import com.example.ChatService.entity.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageResponse toChatMessageResponse(ChatMessage chatMessage);
}
