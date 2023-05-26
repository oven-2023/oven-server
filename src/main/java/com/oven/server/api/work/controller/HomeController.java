package com.oven.server.api.work.controller;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.work.dto.response.GetWorkDto;
import com.oven.server.api.work.service.GetPopularWorkListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Home", description = "홈 화면 API")
public class HomeController {

    private final GetPopularWorkListService getPopularWorkListService;

    @GetMapping("/home/populars")
    public Response<List<GetWorkDto>> getPopularWorkList() {
        try {
            List<GetWorkDto> popularWorkDtoList = getPopularWorkListService.getPopularWorkList();
            return new Response<>(popularWorkDtoList, ResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }





}
