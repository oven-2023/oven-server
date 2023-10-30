package com.oven.server.api.chat.service;

import com.oven.server.api.chat.domain.Chatroom;
import com.oven.server.api.chat.domain.Entrance;
import com.oven.server.api.chat.dto.request.CreateChatroomRequest;
import com.oven.server.api.chat.dto.response.CreateChatroomResponse;
import com.oven.server.api.chat.repository.ChatroomRepository;
import com.oven.server.api.chat.repository.EntranceRepository;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.repository.ProviderRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateChatroomService {

    private final ChatroomRepository chatroomRepository;
    private final ProviderRepository providerRepository;

    private final EntranceRepository entranceRepository;

    public CreateChatroomResponse createChatroom(User user, CreateChatroomRequest request, Long providerId) {

        Chatroom newChatroom = Chatroom.builder()
                .title(request.getTitle())
                .wholeNum(request.getWholeNum())
                .provider(providerRepository.findById(providerId).orElseThrow(
                        () -> new BaseException(ResponseCode.PROVIDER_NOT_FOUND)
                ))
                .build();

        Entrance newEntrance = Entrance.builder()
                .chatroom(newChatroom)
                .user(user)
                .build();

        chatroomRepository.save(newChatroom);
        entranceRepository.save(newEntrance);

        return new CreateChatroomResponse(newChatroom.getId());

    }

}
