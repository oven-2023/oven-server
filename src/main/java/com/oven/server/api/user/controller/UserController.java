package com.oven.server.api.user.controller;

import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.request.UserInfoRequest;
import com.oven.server.api.user.dto.request.UserRequest;
import com.oven.server.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody JoinRequest joinRequest) throws Exception {
        return ResponseEntity
                .ok().body(userService.register(joinRequest));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest userRequest) throws Exception {
        return ResponseEntity
                .ok()
                .body(userService.doLogin(userRequest));
    }

    @GetMapping("/mypage/likes")
    public ResponseEntity getInterestingWork(@RequestBody UserInfoRequest userInfoRequest) {
        return ResponseEntity
                .ok().body(userService.getLikes(userInfoRequest));
    }

    @GetMapping("/mypage/ratings")
    public ResponseEntity getRatingWork(@RequestBody UserInfoRequest userInfoRequest) {
        return ResponseEntity
                .ok().body(userService.getRatings(userInfoRequest));
    }

}
