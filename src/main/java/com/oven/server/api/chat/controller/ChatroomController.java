package com.oven.server.api.chat.controller;

import com.oven.server.api.chat.dto.response.ChatroomListDto;
import com.oven.server.api.chat.service.GetChatroomService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Chatroom", description = "채팅방 API")
public class ChatroomController {

    private final GetChatroomService getChatroomService;

    @Operation(summary = "채팅방 전체 조회 API")
    @GetMapping(value = "/chatrooms")
    public Response<List<ChatroomListDto>> getChatroomList(@RequestParam(value = "providerId", required = false) Long providerId) {

        return Response.success(ResponseCode.SUCCESS_OK, getChatroomService.getChatroomList(providerId));

    }
}
