package com.oven.server.api.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateChatroomResponse {

    @Schema(description = "채팅방 ID", example = "1")
    private Long chatroomId;

    @Builder
    public CreateChatroomResponse(Long chatroomId) {
        this.chatroomId = chatroomId;
    }

}
