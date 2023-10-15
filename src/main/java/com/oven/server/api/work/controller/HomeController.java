package com.oven.server.api.work.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.dto.response.GetWorkDto;
import com.oven.server.api.work.service.GetPopularWorkListService;
import com.oven.server.api.work.service.GetRecommendWorksService;
import com.oven.server.api.work.service.SpringToFlaskService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="Home", value = "홈 화면 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final GetPopularWorkListService getPopularWorkListService;
    private final GetRecommendWorksService getRecommendWorkListService;
    private final SpringToFlaskService springToFlaskService;

    @Operation(summary = "인기 작품 조회 API")
    @GetMapping("/populars")
    public Response<List<GetWorkDto>> getPopularWorkList() {

        try {
            List<GetWorkDto> popularWorkDtoList = getPopularWorkListService.getPopularWorkList();
            return new Response<>(popularWorkDtoList, ResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @GetMapping("/recommendation/works")
    public void springToFlask(@AuthenticationPrincipal User user) {
        springToFlaskService.springToFlask(user);
    }

    @Operation(summary = "추천 작품 조회 API")
    @PostMapping("/recommendation/works")
    public Response<List<GetWorkDto>> getRecommendWorkList(@RequestBody String dataFromFlask) {

        try {
            List<GetWorkDto> recommendWorkDtoList = getRecommendWorkListService.getRecommendWorkList(dataFromFlask);
            return new Response<>(recommendWorkDtoList, ResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

 // /home/recommendation/provider




}
