package com.oven.server.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RefreshTokenRequest {

    @Schema(description = "리프레쉬 토큰", example = "asdlkjfb01sadf03918da23;091")
    private String refreshToken;

}
