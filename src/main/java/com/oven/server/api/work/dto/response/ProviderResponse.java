package com.oven.server.api.work.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProviderResponse {

    @Schema(description="ott id", example = "1")
    private Long providerId;

}
