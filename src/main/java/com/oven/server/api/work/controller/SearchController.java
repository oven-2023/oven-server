package com.oven.server.api.work.controller;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.Response;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.work.dto.response.GetWorkDto;
import com.oven.server.api.work.service.SearchWorkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Search", description = "검색 화면 API")
public class SearchController {

    private final SearchWorkService searchWorkService;

    @GetMapping("/search")
    public Response<List<GetWorkDto>> searchWork(@RequestParam("keyword") String keyword) {
        try {
            List<GetWorkDto> searchedWorkList = searchWorkService.searchWork(keyword);
            return new Response<List<GetWorkDto>>(searchedWorkList, ResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new Response<>(e.getResponseStatus());
        }
    }
}
