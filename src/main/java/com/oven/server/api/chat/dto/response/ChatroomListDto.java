package com.oven.server.api.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatroomListDto {

    @Schema(description = "채팅방 제목", example = "넷플릭스 같이 볼 사람")
    private String title;

    @Schema(description = "현재 채팅방 인원", example = "3")
    private int count;

    @Schema(description = "채팅방 마감 여부")
    private boolean max;

    @Builder
    public ChatroomListDto(String title, int count) {
        this.title = title;
        this.count = count;
        this.max = (count == 4);
    }

}
