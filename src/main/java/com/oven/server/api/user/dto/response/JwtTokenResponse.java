package com.oven.server.api.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenResponse {

    private String accessToken;
    private String refreshToken;

    @Builder
    public JwtTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
