package com.oven.server.api.work.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oven.server.common.response.Response;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.dto.response.GetWorkListDto;
import com.oven.server.api.work.service.GetPopularWorkListService;
import com.oven.server.api.work.service.GetRecommendWorksService;
import com.oven.server.api.work.service.SpringToFlaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Home", description = "홈 화면 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final GetPopularWorkListService getPopularWorkListService;
    private final GetRecommendWorksService getRecommendWorkListService;
    private final SpringToFlaskService springToFlaskService;

    @Operation(summary = "인기 작품 조회 API")
    @GetMapping("/populars")
    public Response<List<GetWorkListDto>> getPopularWorkList() {

        List<GetWorkListDto> popularWorkDtoList = getPopularWorkListService.getPopularWorkList();
        return Response.success(ResponseCode.SUCCESS_OK, popularWorkDtoList);

    }

    @GetMapping("/recommendation/works")
    public void springToFlask(@AuthenticationPrincipal User user) {
        springToFlaskService.springToFlask(user);
    }

    @Operation(summary = "추천 작품 조회 API")
    @PostMapping("/recommendation/works")
    public Response<List<GetWorkListDto>> getRecommendWorkList(@RequestBody String dataFromFlask) {

        try {
            List<GetWorkListDto> recommendWorkDtoList = getRecommendWorkListService.getRecommendWorkList(dataFromFlask);
            return Response.success(ResponseCode.SUCCESS_OK, recommendWorkDtoList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

 // /home/recommendation/provider




}
