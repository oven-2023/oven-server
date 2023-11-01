package com.oven.server.api.chat.controller;

import com.oven.server.api.chat.dto.request.CreateChatroomRequest;
import com.oven.server.api.chat.dto.response.ChatroomListDto;
import com.oven.server.api.chat.dto.response.CreateChatroomResponse;
import com.oven.server.api.chat.dto.response.EnterChatroomResponse;
import com.oven.server.api.chat.service.CreateChatroomService;
import com.oven.server.api.chat.service.EnterChatroomService;
import com.oven.server.api.chat.service.GetChatroomService;
import com.oven.server.api.user.domain.User;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Chatroom", description = "채팅방 API")
public class ChatroomController {

    private final GetChatroomService getChatroomService;
    private final CreateChatroomService createChatroomService;
    private final EnterChatroomService enterChatroomService;

    @Operation(summary = "채팅방 전체 조회 API")
    @GetMapping(value = "/chatrooms")
    public Response<List<ChatroomListDto>> getChatroomList(@RequestParam(value = "providerId", required = false) Long providerId) {

        return Response.success(ResponseCode.SUCCESS_OK, getChatroomService.getChatroomList(providerId));

    }

    @Operation(summary = "채팅방 생성 API")
    @PostMapping(value = "/chatrooms")
    public Response<CreateChatroomResponse> createChatroom(@AuthenticationPrincipal User user,
                                                           @RequestParam(value = "providerId", required = true) Long providerId,
                                                           @RequestBody CreateChatroomRequest request) {
        return Response.success(ResponseCode.SUCCESS_CREATED, createChatroomService.createChatroom(user, request, providerId));
    }

    @Operation(summary = "채팅방 입장 API")
    @PostMapping(value = "/chatrooms/{chatroomId}")
    public Response<EnterChatroomResponse> enterChatroom(@AuthenticationPrincipal User user, @PathVariable(value = "chatroomId") Long chatroomId) {
        return Response.success(ResponseCode.SUCCESS_CREATED, enterChatroomService.enterChatroom(user, chatroomId));
    }

}
