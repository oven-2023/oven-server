package com.oven.server.api.user.controller;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.service.MyPageService;
import com.oven.server.api.work.dto.response.WorkListDto;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "MyPage API", description = "마이페이지 API")
@RequestMapping(value = "/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    @Operation(summary = "찜한 작품 조회")
    @GetMapping("/likes")
    public Response<List<WorkListDto>> getInterestingWork(@AuthenticationPrincipal User user) {
        List<WorkListDto> workList = myPageService.getLikes(user);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

    @Operation(summary = "평가한 작품 조회")
    @GetMapping("/ratings")
    public Response<List<WorkListDto>> getRatingWork(@AuthenticationPrincipal User user) {
        List<WorkListDto> workList = myPageService.getRatings(user);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

}
