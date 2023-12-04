package com.oven.server.api.work.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oven.server.api.work.dto.response.ProviderResponse;
import com.oven.server.api.work.service.GetRecommendProviderService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.dto.response.WorkListDto;
import com.oven.server.api.work.service.GetPopularWorkListService;
import com.oven.server.api.work.service.GetRecommendWorksService;
import com.oven.server.feign.FlaskFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Home", description = "홈 화면 API")
@RestController
//@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final GetRecommendProviderService getRecommendProviderService;
    private final GetPopularWorkListService getPopularWorkListService;
    private final GetRecommendWorksService getRecommendWorkListService;
    private final FlaskFeignClient flaskFeignClient;

    @Operation(summary = "맞춤 OTT 추천 API")
    @GetMapping("/recommendation/providers")
    public Response<ProviderResponse> getRecommendProvider(@AuthenticationPrincipal User user) {

        ProviderResponse result = getRecommendProviderService.getRecommendProvider(user);
        return Response.success(ResponseCode.SUCCESS_OK, result);

    }

    @Operation(summary = "인기 작품 조회 API")
    @GetMapping("/populars")
    public Response<List<WorkListDto>> getPopularWorkList() {

        List<WorkListDto> popularWorkDtoList = getPopularWorkListService.getPopularWorkList();
        return Response.success(ResponseCode.SUCCESS_OK, popularWorkDtoList.subList(0, 1));

    }

    @Operation(summary = "맞춤 작품 추천 API")
    @GetMapping("/recommendation/works")
    public Response<List<WorkListDto>> getRecommendWorkList(@AuthenticationPrincipal User user) {
        return Response.success(ResponseCode.SUCCESS_OK, getRecommendWorkListService.getRecommendWorkList(user));
    }

//    @Operation(summary = "맞춤 작품 추천 API")
//    @GetMapping("/recommendation/works")
//    public Response<List<WorkListDto>> getRecommendWorkList(@AuthenticationPrincipal User user) {
//
//        String userId = String.valueOf(user.getId());
//        String response = flaskFeignClient.getDataFromFlask(userId);
//        try {
//            List<WorkListDto> recommendWorkDtoList = getRecommendWorkListService.getRecommendWorkList(response);
//            return Response.success(ResponseCode.SUCCESS_OK, recommendWorkDtoList);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Autowired
    public HomeController(GetRecommendProviderService getRecommendProviderService, GetPopularWorkListService getPopularWorkListService, GetRecommendWorksService getRecommendWorkListService, FlaskFeignClient flaskFeignClient) {
        this.getRecommendProviderService = getRecommendProviderService;
        this.getPopularWorkListService = getPopularWorkListService;
        this.getRecommendWorkListService = getRecommendWorkListService;
        this.flaskFeignClient = flaskFeignClient;
    }

    // /home/recommendation/provider


}
