package com.oven.server.api.chat.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponse {

    @Schema(description = "메시지 내용", example = "안녕하세요")
    private String content;

    @Schema(description = "메시지 전송 시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm", timezone = "Asia/Seoul")
    private LocalDateTime sendTime;

    @Schema(description = "메시지 발신인 아이디", example = "id2023")
    private String senderUsername;

    @Schema(description = "메시지 발신인 닉네임", example = "오븐조아")
    private String senderNickname;

    @Builder
    public MessageResponse(String content, LocalDateTime sendTime, String senderUsername, String senderNickname) {
        this.content = content;
        this.sendTime = sendTime;
        this.senderUsername = senderUsername;
        this.senderNickname = senderNickname;
    }

}
