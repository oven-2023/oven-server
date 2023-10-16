package com.oven.server.api.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginSuccessResponse {

    @Schema(description = "JWT 토큰")
    private JwtTokenResponse jwtTokenResponse;
    @Schema(description = "유저 닉네임", example = "오븐좋아")
    private String nickname;

    @Builder
    public LoginSuccessResponse(JwtTokenResponse jwtTokenResponse, String nickname) {
        this.jwtTokenResponse = jwtTokenResponse;
        this.nickname = nickname;
    }

}
