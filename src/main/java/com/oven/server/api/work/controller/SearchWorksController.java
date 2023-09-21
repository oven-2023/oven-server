package com.oven.server.api.work.controller;

import com.oven.server.api.work.dto.GetWorkListDto;
import com.oven.server.api.work.service.SearchWorksService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Search API", description = "작품 검색 API")
public class SearchWorksController {

    private final SearchWorksService searchWorkService;

    @Operation(description = "작품 검색 API")
    @GetMapping("/search")
    public Response<List<GetWorkListDto>> searchWork(@RequestParam("keyword") String keyword) {
        List<GetWorkListDto> workList = searchWorkService.searchWork(keyword);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

}
