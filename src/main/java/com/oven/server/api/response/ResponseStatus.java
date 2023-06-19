package com.oven.server.api.response;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    SUCCESS(true, 200, "요청이 성공하였습니다."),
    SUCCESS_POST(true, 201, "요청이 성공하였습니다."),

    FAIL_REGISTER(false, 400, "회원가입이 실패하였습니다."),
    WORK_NOT_FOUND(false, 404, "해당 작품이 존재하지 않습니다"),
    USER_NOT_FOUND(false, 400, "유저를 찾을 수 없습니다."),

    NOT_ENOUGH_DATA(true, 200, "데이터가 충분하지 않습니다.");


    private final Boolean isSuccess;
    private final int code;
    private final String message;

}
