package com.oven.server.api.work.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostLikeResponse {

    @Schema(description = "좋아요 여부")
    private boolean liked;

    @Builder
    public PostLikeResponse(boolean liked) {
        this.liked = liked;
    }

}
