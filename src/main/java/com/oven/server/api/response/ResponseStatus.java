package com.oven.server.api.response;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    SUCCESS(true, 200, "요청이 성공하였습니다."),
    WORK_NOT_FOUND(false, 404, "해당 작품이 존재하지 않습니다"),
    USER_NOT_FOUND(false, 400, "유저를 찾을 수 없습니다.");


    private final Boolean isSuccess;
    private final int code;
    private final String message;

}
