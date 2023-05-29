package com.oven.server.api.user.controller;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.user.domain.User;

import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.request.UserRequest;
import com.oven.server.api.user.service.UserService;
import com.oven.server.api.work.dto.response.GetWorkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response join(@RequestBody JoinRequest joinRequest) throws BaseException {
        try {
            userService.register(joinRequest);
            return new Response<>(ResponseStatus.SUCCESS_POST);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest userRequest) throws Exception {
        return ResponseEntity
                .ok()
                .body(userService.doLogin(userRequest));
    }

    @GetMapping("/mypage/likes")
    public Response<List<GetWorkDto>> getInterestingWork(@AuthenticationPrincipal User user) {

        List<GetWorkDto> workList = userService.getLikes(user);
        return new Response<List<GetWorkDto>>(workList, ResponseStatus.SUCCESS);

    }

    @GetMapping("/mypage/ratings")
    public Response<List<GetWorkDto>> getRatingWork(@AuthenticationPrincipal User user) {

        List<GetWorkDto> workList = userService.getRatings(user);
        return new Response<List<GetWorkDto>>(workList, ResponseStatus.SUCCESS);

    }

}
