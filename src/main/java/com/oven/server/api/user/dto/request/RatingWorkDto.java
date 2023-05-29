package com.oven.server.api.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@ApiModel(value="작품 평가", description="작품 별점으로 평가")
public class RatingWorkDto {

    @ApiModelProperty(value = "평점", example = "4")
    @Min(0)
    @Max(5)
    private int rating;

}
