package com.oven.server.api.chat.service;


import com.oven.server.api.chat.domain.Chatroom;
import com.oven.server.api.chat.domain.Message;
import com.oven.server.api.chat.dto.request.MessageRequest;
import com.oven.server.api.chat.dto.response.MessageResponse;
import com.oven.server.api.chat.repository.ChatroomRepository;
import com.oven.server.api.chat.repository.MessageRepository;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageService {

    private final SimpMessagingTemplate template;
    private final MessageRepository messageRepository;
    private final ChatroomRepository chatroomRepository;
    private final UserRepository userRepository;

    public void sendMessage(Long chatroomId, MessageRequest messageRequest) {

        Chatroom chatroom = chatroomRepository.findById(chatroomId).orElseThrow(
                () -> new BaseException(ResponseCode.CHATROOM_NOT_FOUND)
        );

        User user = userRepository.findByUsername(messageRequest.getSenderUsername()).orElseThrow(
                () -> new BaseException(ResponseCode.USER_NOT_FOUND)
        );

        Message message = Message.builder()
                .sender(user)
                .content(messageRequest.getContent())
                .chatroom(chatroom)
                .build();

        messageRepository.save(message);

        MessageResponse messageResponse = MessageResponse.builder()
                .content(message.getContent())
                .sendTime(message.getCreatedAt())
                .senderUsername(user.getUsername())
                .senderNickname(user.getNickname())
                .build();

        template.convertAndSend("/sub/chatrooms/" + chatroomId + "/message", messageResponse);

    }

}
