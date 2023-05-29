package com.oven.server.api.user.controller;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.request.RatingWorkDto;
import com.oven.server.api.user.service.RatingWorkService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags="Rating Work")
public class RatingWorkController {

    private final RatingWorkService ratingWorkService;

    @Operation(summary = "작품 평점 입력")
    @PostMapping("works/{workId}/rating")
    public Response ratingWork(@AuthenticationPrincipal User user, @PathVariable("workId") Long workId, @RequestBody RatingWorkDto ratingWorkDto) {
        try {
            ratingWorkService.postRatingWork(user, workId, ratingWorkDto);
            return new Response<>(ResponseStatus.SUCCESS_POST);
        } catch (BaseException e) {
            return new Response(e.getResponseStatus());
        }
    }

}
