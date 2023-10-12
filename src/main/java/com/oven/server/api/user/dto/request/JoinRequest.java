package com.oven.server.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class JoinRequest {

    @Schema(description = "닉네임", example = "오븐좋아")
    private String nickname;

    @Schema(description = "아이디", example = "id2023")
    private String username;

    @Schema(description = "패스워드", example = "password123@")
    private String password;

}
