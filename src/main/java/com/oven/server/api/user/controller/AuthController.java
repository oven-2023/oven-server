package com.oven.server.api.user.controller;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.request.IdCheckRequest;
import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.request.LoginRequest;
import com.oven.server.api.user.dto.request.RefreshTokenRequest;
import com.oven.server.api.user.dto.response.AccessTokenResponse;
import com.oven.server.api.user.dto.response.IdCheckResponse;
import com.oven.server.api.user.dto.response.JwtTokenResponse;
import com.oven.server.api.user.dto.response.LoginSuccessResponse;
import com.oven.server.api.user.service.AuthService;
import com.oven.server.api.work.dto.response.SearchResultResponse;
import com.oven.server.api.work.service.SearchWorksService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Auth API", description = "회원가입/로그인 API")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final SearchWorksService searchWorksService;

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

    @Operation(summary = "회원가입시 작품 선택")
    @GetMapping(value = "/join/works")
    public Response<SearchResultResponse> getWorks(@RequestParam(value = "size") int size,
                                                   @RequestParam(value = "workId", required = false) Long workId,
                                                   @RequestParam(value = "keyword", required = false) String keyword) {
        return Response.success(ResponseCode.SUCCESS_OK, searchWorksService.searchWork(size, workId, keyword));
    }

    @Operation(summary = "로그인")
    @PostMapping(value = "/login")
    public Response<LoginSuccessResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginSuccessResponse loginSuccessResponse = authService.login(loginRequest);
        return Response.success(ResponseCode.SUCCESS_OK, loginSuccessResponse);
    }

    @Operation(summary = "로그아웃")
    @PostMapping(value = "/logout")
    public Response<Void> signOut(@AuthenticationPrincipal User user, @RequestBody RefreshTokenRequest refreshTokenRequest) {
        authService.logout(user, refreshTokenRequest);
        return Response.success(ResponseCode.SUCCESS_OK);
    }

    @Operation(summary = "리프레쉬 토큰으로 액세스 토큰 재발급")
    @PostMapping(value = "/reissuance")
    public Response<AccessTokenResponse> reissueAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        AccessTokenResponse accessTokenDto = authService.reissueAccessToken(refreshTokenRequest);
        return Response.success(ResponseCode.SUCCESS_CREATED, accessTokenDto);
    }

}
