package com.oven.server.api.user.controller;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.service.MyPageService;
import com.oven.server.api.work.dto.GetWorkListDto;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/mypage/likes")
    public Response<List<GetWorkListDto>> getInterestingWork(@AuthenticationPrincipal User user) {
        List<GetWorkListDto> workList = myPageService.getLikes(user);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

    @GetMapping("/mypage/ratings")
    public Response<List<GetWorkListDto>> getRatingWork(@AuthenticationPrincipal User user) {
        List<GetWorkListDto> workList = myPageService.getRatings(user);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

}
