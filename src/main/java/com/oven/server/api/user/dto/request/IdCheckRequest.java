package com.oven.server.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class IdCheckRequest {

    @Schema(description = "아이디", example = "id2023")
    private String username;

}
