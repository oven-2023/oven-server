package com.oven.server.api.work.controller;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.work.dto.response.GetWorkDetailDto;
import com.oven.server.api.work.service.GetWorkDetailService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags="Work Detail")
public class WorkDetailController {

    private final GetWorkDetailService getWorkDetailService;

    @Operation(summary = "작품 상세 API")
    @GetMapping("/works/{workId}")
    public Response<GetWorkDetailDto> getRecommendWorkList(@PathVariable("workId") Long workId) {
        try {
            GetWorkDetailDto workDetailDto = getWorkDetailService.getWorkDetail(workId);
            return new Response<>(workDetailDto, ResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }
}
