package com.oven.server.config.jwt;

import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.oven.server.common.response.Response.setJsonExceptionResponse;

public class JwtExceptionHandler {

    public static void handle(HttpServletResponse response, ResponseCode exception) throws BaseException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setContentType("application/json");
        response.setStatus(exception.getHttpStatus().value());
        response.getWriter().print(setJsonExceptionResponse(exception));

    }
}
