package com.oven.server.api.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatroomListDto {

    @Schema(description = "채팅방 ID", example = "14")
    private Long chatroomId;

    @Schema(description = "OTT ID", example = "2")
    private Long providerId;
    @Schema(description = "채팅방 제목", example = "넷플릭스 같이 볼 사람")
    private String title;

    @Schema(description = "현재 채팅방 인원", example = "3")
    private int count;

    @Schema(description = "채팅방 최대 인원", example = "4")
    private int wholeNum;

    @Schema(description = "채팅방 마감 여부", example = "false")
    private boolean max;


    @Builder
    public ChatroomListDto(Long chatroomId, Long providerId, String title, int count, int wholeNum) {
        this.chatroomId = chatroomId;
        this.providerId = providerId;
        this.title = title;
        this.count = count;
        this.wholeNum = wholeNum;
        this.max = (count == wholeNum);
    }

}
