package com.example.ChatService.repository;

import com.example.ChatService.dto.response.ChatMessageResponse;
import com.example.ChatService.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage,String> {
    List<ChatMessage> findAllByConversationIdOrderByCreatedDateDesc(String conversationId);
}
