package com.oven.server.api.user.dto.request;

import lombok.Getter;

@Getter
public class JoinRequest {

    private String nickName;
    private String userName;
    private String password;
    private String pwConfirm;

}
