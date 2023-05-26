package com.oven.server.api.user.contoller;

import com.oven.server.api.user.dto.JoinRequest;
import com.oven.server.api.user.dto.UserRequest;
import com.oven.server.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

}
