package com.oven.server.api.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccessTokenResponse {

    private String accessToken;

    @Builder
    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
