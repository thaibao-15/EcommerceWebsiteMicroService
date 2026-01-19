package com.example.ChatService.mapper;

import com.example.ChatService.dto.response.ConversationResponse;
import com.example.ChatService.entity.Conversation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    ConversationResponse toConversationResponse(Conversation conversation);
}
