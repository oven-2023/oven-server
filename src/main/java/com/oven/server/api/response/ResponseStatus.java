package com.oven.server.api.response;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    SUCCESS(true, 200, "요청이 성공하였습니다.");


    private final Boolean isSuccess;
    private final int code;
    private final String message;

}
