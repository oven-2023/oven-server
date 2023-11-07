package com.oven.server.api.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponse {

    @Schema(description = "메시지 내용", example = "안녕하세요")
    private String content;

    @Schema(description = "메시지 전송 시간")
    private LocalDateTime sendTime;

    @Schema(description = "메시지 보낸 유저 닉네임", example = "오븐조아")
    private String senderNickname;

    @Builder
    public MessageResponse(String content, LocalDateTime sendTime, String senderNickname) {
        this.content = content;
        this.sendTime = sendTime;
        this.senderNickname = senderNickname;
    }

}
