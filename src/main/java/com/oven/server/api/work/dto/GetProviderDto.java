package com.oven.server.api.work.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(name="OTT 이름", example = "NETFLIX")
public class GetProviderDto {

    @Schema(name="OTT 이름", example = "NETFLIX")
    private String name;

}
