package com.oven.server.api.work.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "작품 리스트 응답 DTO")
public class GetWorkListDto {

    @Schema(name="작품 id", example = "1")
    private final Long workId;

    @Schema(name="작품 제목", example = "택배 기사")
    private final String title;

    @Schema(name="포스터 이미지 주소", example = "https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/drama_poster_images/280x400/drama_102591_1681959812.jpg")
    private final String poster;

}
