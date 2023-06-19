package com.oven.server.api.work.controller;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.dto.response.GetProviderDto;
import com.oven.server.api.work.dto.response.GetWorkDto;
import com.oven.server.api.work.service.GetPopularWorkListService;
import com.oven.server.api.work.service.GetRecommendWorksService;
import com.oven.server.api.work.service.RecommendProviderService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="Home", value = "홈 화면 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final GetPopularWorkListService getPopularWorkListService;
    private final GetRecommendWorksService getRecommendWorkListService;
    private final RecommendProviderService recommendProviderService;

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

    @Operation(summary = "추천 작품 조회 API")
    @GetMapping("/recommendation/works")
    public Response<List<GetWorkDto>> getRecommendWorkList() {
        try {
            List<GetWorkDto> recommendWorkDtoList = getRecommendWorkListService.getRecommendWorkList();
            return new Response<>(recommendWorkDtoList, ResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }

    @Operation(summary = "추천 OTT 조회")
    @GetMapping("/recommendation/provider")
    public Response<GetProviderDto> getRecommendProvider(@AuthenticationPrincipal User user) throws BaseException {

        try {
            GetProviderDto getProviderDto = recommendProviderService.recommendProvider(user);
            return new Response<>(getProviderDto, ResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }

    }




}
