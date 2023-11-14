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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm", timezone = "Asia/Seoul")
    private LocalDateTime sendTime;

    @Schema(description = "지금 로그인해있는 유저가 이 메시지를 보낸 사람인지(이거 swagger에 sender라고 뜨는데 isSender임!!")
    private boolean isSender;

    @Schema(description = "메시지 발신 유저 닉네임", example = "오븐조아")
    private String senderNickname;

    @Builder
    public MessageListResponse(String content, LocalDateTime sendTime, boolean isSender, String senderNickname) {
        this.content = content;
        this.sendTime = sendTime;
        this.isSender = isSender;
        this.senderNickname = senderNickname;
    }

}
