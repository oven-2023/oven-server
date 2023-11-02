package com.oven.server.api.chat.service;

import com.oven.server.api.chat.domain.Chatroom;
import com.oven.server.api.chat.dto.response.ChatroomListDto;
import com.oven.server.api.chat.repository.ChatroomRepository;
import com.oven.server.api.chat.repository.EntranceRepository;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.domain.Provider;
import com.oven.server.api.work.repository.ProviderRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetChatroomService {

    private final ChatroomRepository chatroomRepository;
    private final ProviderRepository providerRepository;
    private final EntranceRepository entranceRepository;

    public List<ChatroomListDto> getChatroomList(Long providerId) {

        List<Chatroom> findChatrooms = new ArrayList<>();

        if(providerId == null) {
           findChatrooms = chatroomRepository.findAll();
        } else {
            Provider provider = providerRepository.findById(providerId).orElseThrow(
                    () -> new BaseException(ResponseCode.PROVIDER_NOT_FOUND)
            );
            findChatrooms = chatroomRepository.findByProvider(provider);
        }

        return findChatrooms
                .stream()
                .map(
                        chatroom -> ChatroomListDto.builder()
                                .chatroomId(chatroom.getId())
                                .providerId(chatroom.getProvider().getId())
                                .title(chatroom.getTitle())
                                .count(chatroom.getCount())
                                .wholeNum(chatroom.getWholeNum())
                                .build()
                ).collect(Collectors.toList());

    }

    public List<ChatroomListDto> getMyChatroomList(User user) {

        return entranceRepository.findByUser(user)
                .stream()
                .map(
                        entrance -> ChatroomListDto.builder()
                                .chatroomId(entrance.getChatroom().getId())
                                .providerId(entrance.getChatroom().getProvider().getId())
                                .title(entrance.getChatroom().getTitle())
                                .count(entrance.getChatroom().getCount())
                                .wholeNum(entrance.getChatroom().getWholeNum())
                                .build()
                ).collect(Collectors.toList());

    }

}
