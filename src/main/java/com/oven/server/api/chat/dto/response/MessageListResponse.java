package com.oven.server.api.chat.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageListResponse {

    @Schema(description = "메시지 내용", example = "안녕하세요")
    private String content;

    @Schema(description = "메시지 전송 시간", example = "19:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:MM", timezone = "Asia/Seoul")
    private LocalDateTime sendTime;

    @Schema(description = "지금 로그인해있는 유저가 이 메시지를 보낸 사람인지")
    private boolean isSender;

    @Builder
    public MessageListResponse(String content, LocalDateTime sendTime, boolean isSender) {
        this.content = content;
        this.sendTime = sendTime;
        this.isSender = isSender;
    }

}
