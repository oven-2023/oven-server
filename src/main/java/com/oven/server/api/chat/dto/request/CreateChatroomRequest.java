package com.oven.server.api.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateChatroomRequest {

    @Size(max = 10)
    @Schema(description = "채팅방 제목", example = "넷플릭스 같이 볼 사람")
    private String title;

    @Schema(description = "채팅방 최대 인원", example = "4")
    private int wholeNum;

}
