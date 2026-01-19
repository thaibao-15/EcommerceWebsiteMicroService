package com.example.ChatService.service;

import com.example.ChatService.dto.request.ChatMessageRequest;
import com.example.ChatService.dto.response.ChatMessageResponse;
import com.example.ChatService.dto.response.UserProfileResponse;
import com.example.ChatService.entity.ChatMessage;
import com.example.ChatService.entity.ParticipantInfo;
import com.example.ChatService.exception.AppException;
import com.example.ChatService.exception.ErrorCode;
import com.example.ChatService.mapper.ChatMessageMapper;
import com.example.ChatService.repository.ChatMessageRepository;
import com.example.ChatService.repository.ConversationRepository;
import com.example.ChatService.repository.httpclient.ProfileClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatMessageService {
    ChatMessageMapper chatMessageMapper;
    ChatMessageRepository chatMessageRepository;
    ConversationRepository conversationRepository;
    ProfileClient profileClient;

    public List<ChatMessageResponse> getMessage(String conversationId){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        var conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        conversation.getParticipants()
                .stream()
                .filter(participantInfo -> userId.equals(participantInfo.getUserId()))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));

        List<ChatMessage> chatMessageResponseList =
                chatMessageRepository.findAllByConversationIdOrderByCreatedDateDesc(conversationId);
        return chatMessageResponseList.stream().map(this::toChatMessageResponse).toList();
    }


    public ChatMessageResponse createChat(ChatMessageRequest request){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        var conversation = conversationRepository.findById(request.getConversationId())
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));

        conversation.getParticipants()
                .stream()
                .filter(participantInfo -> userId.equals(participantInfo.getUserId()))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));

        var userInfo = profileClient.getMyInfo();
        UserProfileResponse userProfileResponse = userInfo.getResult();

        ChatMessage chatMessage = ChatMessage.builder()
                .conversationId(request.getConversationId())
                .message(request.getMessage())
                .sender(ParticipantInfo.builder()
                        .userId(userProfileResponse.getId())
                        .username(userProfileResponse.getUsername())
                        .firstName(userProfileResponse.getFirstName())
                        .lastName(userProfileResponse.getLastName())
                        .avatar(userProfileResponse.getAvatar())
                        .build())
                .createdDate(Instant.now())
                .build();
        chatMessageRepository.save(chatMessage);
        return toChatMessageResponse(chatMessage);
    }

    public ChatMessageResponse toChatMessageResponse(ChatMessage chatMessage){
        String idUser = SecurityContextHolder.getContext().getAuthentication().getName();
        ChatMessageResponse chatMessageResponse = chatMessageMapper.toChatMessageResponse(chatMessage);
        chatMessageResponse.setMe(idUser.equals(chatMessage.getSender().getUserId()));
        return chatMessageResponse;
    }
}
