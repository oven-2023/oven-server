package com.oven.server.api.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class IdCheckResponse {

    @Schema(description = "아이디", example = "오븐좋아")
    private boolean idExists;

    @Builder
    public IdCheckResponse(boolean idExists) {
        this.idExists = idExists;
    }
}
