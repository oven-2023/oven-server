package com.oven.server.api.user.controller;

import com.oven.server.api.user.dto.request.IdCheckRequest;
import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.response.IdCheckResponse;
import com.oven.server.api.user.service.AuthService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Auth API", description = "회원가입/로그인 API")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "아이디 중복 확인")
    @PostMapping(value = "/id/exists")
    public Response<IdCheckResponse> idDuplicateCheck(@RequestBody IdCheckRequest idCheckRequest) {
        IdCheckResponse idCheckResponse = authService.idDuplicateCheck(idCheckRequest);
        return Response.success(ResponseCode.SUCCESS_OK, idCheckResponse);
    }

    @Operation(summary = "회원가입")
    @PostMapping(value = "/join")
    public Response<Void> join(@RequestBody JoinRequest joinRequest) {
        authService.join(joinRequest);
        return Response.success(ResponseCode.SUCCESS_CREATED);
    }



}
