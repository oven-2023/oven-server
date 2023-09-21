package com.oven.server.common.exception;

import com.oven.server.common.response.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {

    private final ResponseCode exceptionCode;

}
