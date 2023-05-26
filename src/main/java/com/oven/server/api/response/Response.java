package com.oven.server.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class Response<T> {

    private final Boolean isSuccess;
    private final int code;
    private final String message;
    private T data;


    public Response(ResponseStatus responseStatus) {
        this.isSuccess = responseStatus.getIsSuccess();
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
    }

    public Response(T data, ResponseStatus responseStatus) {
        this.isSuccess = responseStatus.getIsSuccess();
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.data = data;
    }

}
