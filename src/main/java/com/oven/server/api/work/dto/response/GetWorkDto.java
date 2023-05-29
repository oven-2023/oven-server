package com.oven.server.api.work.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(value="작품 정보", description = "작품 리스트 조회용(작품 제목, 포스터 이미지 주소)")
public class GetWorkDto {

    @ApiModelProperty(value="작품 id", example = "1")
    private final Long workId;

    @ApiModelProperty(value="작품 제목", example = "택배 기사")
    private final String title;

    @ApiModelProperty(value="포스터 이미지 주소", example = "https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/drama_poster_images/280x400/drama_102591_1681959812.jpg")
    private final String poster;

}
