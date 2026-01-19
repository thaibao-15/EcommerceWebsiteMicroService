package com.example.ChatService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ChatService.entity.Conversation;

import java.util.Optional;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
    Optional<Conversation> findByParticipantsHash(String userIdHash);
}
