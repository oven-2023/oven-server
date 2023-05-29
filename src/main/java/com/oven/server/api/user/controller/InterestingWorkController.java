package com.oven.server.api.user.controller;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.service.InterestingWorkService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags="Interesting Work")
public class InterestingWorkController {

    private final InterestingWorkService interestingWorkService;

    @Operation(summary = "작품 좋아요")
    @PostMapping("works/{workId}/like")
    public Response likeWork(@AuthenticationPrincipal User user, @PathVariable("workId") Long workId) {
        try {
            interestingWorkService.postInterestingWork(user, workId);
            return new Response(ResponseStatus.SUCCESS_POST);
        } catch (BaseException e) {
            return new Response(e.getResponseStatus());
        }
    }

}
