package com.oven.server.api.work.controller;

import com.oven.server.api.work.dto.response.WorkListDto;
import com.oven.server.api.work.service.SearchWorksService;
import com.oven.server.common.response.Response;
import com.oven.server.common.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Search API", description = "작품 검색 API")
public class SearchWorksController {

    private final SearchWorksService searchWorkService;

    @Operation(summary = "작품 제목 검색")
    @GetMapping("/search")
    public Response<List<WorkListDto>> searchWork(Pageable pageable,
                                                  @RequestParam(value = "workId", required = false) Long workId,
                                                  @RequestParam(value = "keyword", required = false) String keyword) {
        List<WorkListDto> workList = searchWorkService.searchWork(pageable, workId, keyword);
        return Response.success(ResponseCode.SUCCESS_OK, workList);
    }

}
