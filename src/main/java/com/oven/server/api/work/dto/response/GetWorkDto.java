package com.oven.server.api.work.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetWorkDto {

    private final String title;

    private final String poster;

}
