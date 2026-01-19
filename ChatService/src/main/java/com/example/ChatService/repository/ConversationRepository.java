package com.example.ChatService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ChatService.entity.Conversation;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
    Optional<Conversation> findByParticipantsHash(String userIdHash);
    @Query("{'participants.userId' : ?0}")
    List<Conversation> findAllByParticipantIdsContains(String id);
}
