package com.oven.server.api.chat.service;

import com.oven.server.api.chat.domain.Chatroom;
import com.oven.server.api.chat.domain.Entrance;
import com.oven.server.api.chat.dto.response.EnterChatroomResponse;
import com.oven.server.api.chat.dto.response.MessagesResponse;
import com.oven.server.api.chat.repository.ChatroomRepository;
import com.oven.server.api.chat.repository.EntranceRepository;
import com.oven.server.api.chat.repository.MessageRepository;
import com.oven.server.api.user.domain.User;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EnterChatroomService {

    private final ChatroomRepository chatroomRepository;
    private final EntranceRepository entranceRepository;
    private final MessageRepository messageRepository;

    public EnterChatroomResponse enterChatroom(User user, Long chatroomId) {

        Chatroom chatroom = chatroomRepository.findById(chatroomId).orElseThrow(
                () -> new BaseException(ResponseCode.CHATROOM_NOt_FOUND)
        );

        if(entranceRepository.findByUserAndChatroom(user, chatroom).isPresent()) {
            return EnterChatroomResponse.builder()
                    .providerId(chatroom.getProvider().getId())
                    .title(chatroom.getTitle())
                    .count(chatroom.getCount())
                    .wholeNum(chatroom.getWholeNum())
                    .isNewEnter(false)
                    .messages(messageRepository.findByChatroom(chatroom).stream()
                            .map(
                                    message -> MessagesResponse.builder()
                                            .content(message.getContent())
                                            .isSender(message.getSender() == user)
                                            .sendTime(message.getCreatedAt())
                                            .build()
                            )
                            .collect(Collectors.toList())
                    )
                    .build();
        }

        Entrance newEntrance = new Entrance(user, chatroom);
        entranceRepository.save(newEntrance);

        chatroom.enter();


        return EnterChatroomResponse.builder()
                .providerId(chatroom.getProvider().getId())
                .title(chatroom.getTitle())
                .count(chatroom.getCount())
                .wholeNum(chatroom.getWholeNum())
                .isNewEnter(true)
                .messages(null)
                .build();

    }


}
