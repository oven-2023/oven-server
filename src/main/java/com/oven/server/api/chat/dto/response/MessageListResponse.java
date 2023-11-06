package com.oven.server.api.chat.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageListResponse {

    private String content;

    private LocalDateTime sendTime;

    private boolean isSender;

    @Builder
    public MessageListResponse(String content, LocalDateTime sendTime, boolean isSender) {
        this.content = content;
        this.sendTime = sendTime;
        this.isSender = isSender;
    }

}