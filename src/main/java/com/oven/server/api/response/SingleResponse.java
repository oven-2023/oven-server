package com.oven.server.api.response;

import lombok.Getter;

@Getter
public class SingleResponse<T> extends BaseResponse {

    T data;

}
