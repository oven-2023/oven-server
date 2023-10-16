package com.oven.server.api.work.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchResultResponse {

    @Schema(description = "다음 페이지 유무", example = "true")
    private boolean nextPage;
    private List<WorkListDto> workListDtos;

    @Builder
    public SearchResultResponse(boolean nextPage, List<WorkListDto> workListDtos) {
        this.nextPage = nextPage;
        this.workListDtos = workListDtos;
    }

}
