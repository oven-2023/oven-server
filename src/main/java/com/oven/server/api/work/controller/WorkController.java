package com.oven.server.api.work.controller;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.dto.request.PostRatingDto;
import com.oven.server.api.work.dto.response.GetWorkDetailDto;
import com.oven.server.api.work.service.GetWorkDetailService;
import com.oven.server.api.work.service.PostInterestingWorkService;
import com.oven.server.api.work.service.PostRatingWorkService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Work API", description = "작품 API")
public class WorkController {

    private final GetWorkDetailService getWorkDetailService;
    private final PostInterestingWorkService postInterestingWorkService;
    private final PostRatingWorkService postRatingWorkService;

    @Operation(summary = "작품 상세 조회")
    @GetMapping("/works/{workId}")
    public Response<GetWorkDetailDto> getRecommendWorkList(@PathVariable("workId") Long workId) {
        GetWorkDetailDto workDetailDto = getWorkDetailService.getWorkDetail(workId);
        return Response.success(ResponseCode.SUCCESS_OK, workDetailDto);
    }

    @Operation(summary = "작품 좋아요")
    @PostMapping("works/{workId}/like")
    public Response<Void> postInterestingWork(@AuthenticationPrincipal User user, @PathVariable("workId") Long workId) {
        postInterestingWorkService.postInterestingWork(user, workId);
        return Response.success(ResponseCode.SUCCESS_CREATED);
    }

    @Operation(summary = "작품 평점 추가")
    @PostMapping("works/{workId}/rating")
    public Response<Void> postRatingWork(@AuthenticationPrincipal User user, @PathVariable("workId") Long workId,
                                         @RequestBody PostRatingDto postRatingDto) {
        postRatingWorkService.postRatingWork(user, workId, postRatingDto);
        return Response.success(ResponseCode.SUCCESS_CREATED);
    }

}