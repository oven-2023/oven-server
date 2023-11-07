package com.oven.server.api.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MessageRequest {

    @Schema(description = "메시지 내용", example = "안녕하세요")
    private String content;

    @Schema(description = "메시지 발신인 ID", example = "id2023")
    private String senderId;

}