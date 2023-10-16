package com.oven.server.api.work.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Schema(description="작품 상세 조회용(작품 제목, 포스터 이미지 주소)")
public class WorkDetailDto {

    @Schema(description = "작품 제목(한글)", example = "택배 기사")
    private String titleKr;

    @Schema(description = "작품 제목(영어)", example = "Black Knight")
    private String titleEng;

    @Schema(description = "개봉 연도", example = "2023")
    private int year;

    @Schema(description = "관람연령등급", example = "15세이상관람가")
    private String rating;

    @Schema(description = "감독", example = "조의석")
    private String director;

    @Schema(description = "배우", example = "김우빈, 송승헌, 이솜, 강유석, 김의성, 이성욱, 이이담")
    private String actor;

    @Schema(description = "작품 줄거리", example = "극심한 대기 오염으로 산소호흡기 없이는 살아갈 수 없는 2071년, 비범한 싸움 실력을 갖춘 전설의 택배기사 ‘5-8’이 난민들의 유일한 희망인 택배기사를 꿈꾸는 난민 ‘사월’을 만나며 벌어지는 일을 그린 넷플릭스 시리즈")
    private String summary;

    @Schema(description = "포스터 이미지 주소", example = "https://nujhrcqkiwag1408085.cdn.ntruss.com/static/upload/drama_poster_images/280x400/drama_102591_1681959812.jpg")
    private String poster;

    @Schema(description = "제공 OTT", example = "넷플릭스, 왓챠, 디즈니+")
    private List<ProviderDto> providerList;

    @Schema(description = "장르", example = "SF, 액션, 드라마, 스릴러, 미스터리")
    private String genre;

}
