package com.oven.server.api.work.controller;

import com.oven.server.api.work.dto.GetWorkDetailDto;
import com.oven.server.api.work.service.GetWorkDetailService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Work Detail API", description = "작품 상세 조회 API")
public class WorkController {

    private final GetWorkDetailService getWorkDetailService;

    @Operation(summary = "작품 상세 조회")
    @GetMapping("/works/{workId}")
    public Response<GetWorkDetailDto> getRecommendWorkList(@PathVariable("workId") Long workId) {
        GetWorkDetailDto workDetailDto = getWorkDetailService.getWorkDetail(workId);
        return Response.success(ResponseCode.SUCCESS_OK, workDetailDto);
    }

}
