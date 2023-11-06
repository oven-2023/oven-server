package com.oven.server.api.chat.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class MessageResponse {

    private String content;

    private LocalDateTime sendTime;

    private String senderNickname;

    @Builder
    public MessageResponse(String content, LocalDateTime sendTime, String senderNickname) {
        this.content = content;
        this.sendTime = sendTime;
        this.senderNickname = senderNickname;
    }

}
