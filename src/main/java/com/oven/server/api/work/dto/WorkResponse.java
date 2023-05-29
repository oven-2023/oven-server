package com.oven.server.api.work.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WorkResponse {

    private String titleKr;

    private String titleEng;

    private int year;

    private String poster;

}
