package com.oven.server.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Oven 2023 API Document",
                description = "2023 홍익대학교 컴퓨터공학과 졸업 프로젝트 Oven API 명세서",
                version = "1.0.0"))
@Configuration
public class SwaggerConfig {
}
