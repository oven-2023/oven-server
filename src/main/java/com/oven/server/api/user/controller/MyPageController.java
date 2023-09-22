package com.oven.server.api.user.controller;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.service.MyPageService;
import com.oven.server.api.work.dto.response.GetWorkListDto;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "MyPage API", description = "마이페이지 API")
public class MyPageController {

    private final MyPageService myPageService;

    @Operation(summary = "찜한 작품 조회")
    @GetMapping("/mypage/likes")
    public Response<List<GetWorkListDto>> getInterestingWork(@AuthenticationPrincipal User user) {
        List<GetWorkListDto> workList = myPageService.getLikes(user);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

    @Operation(summary = "평가한 작품 조회")
    @GetMapping("/mypage/ratings")
    public Response<List<GetWorkListDto>> getRatingWork(@AuthenticationPrincipal User user) {
        List<GetWorkListDto> workList = myPageService.getRatings(user);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

}
