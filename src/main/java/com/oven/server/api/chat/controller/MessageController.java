package com.oven.server.api.chat.controller;

import com.oven.server.api.chat.dto.request.MessageRequest;
import com.oven.server.api.chat.service.MessageService;
import com.oven.server.api.user.domain.User;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Message", description = "메세지 API")
public class MessageController {

    private final MessageService messageService;

    @Operation(tags = "Message", description = "메세지 전송")
    @MessageMapping(value = "/chatrooms/{chatroomId}/message")
    public Response<Void> sendMessage(@PathVariable(value = "chatroomId") Long chatroomId,
                                      @AuthenticationPrincipal User user,
                                      @RequestBody MessageRequest messageRequest) {

        messageService.sendMessage(chatroomId, user, messageRequest);
        return Response.success(ResponseCode.SUCCESS_CREATED);

    }

}
