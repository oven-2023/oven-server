package com.oven.server.api.response;

import lombok.Getter;

@Getter
public class BaseResponse {

    Boolean success;
    int code;
    String message;

}
