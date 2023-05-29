package com.oven.server.api.work.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class WorkListResponse {

    private final List<WorkResponse> workList;

    @Builder
    public WorkListResponse(List<WorkResponse> workResponses) {
        this.workList = workResponses;
    }

}
