package com.oven.server.api.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    private String message;
    private String token;

}
