package com.oven.server.api.work.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostRatingDto {

    @Schema(description = "평점", example = "3")
    private Integer rating;

}
