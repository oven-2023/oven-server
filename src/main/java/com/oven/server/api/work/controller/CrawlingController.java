package com.oven.server.api.work.controller;

import com.oven.server.api.work.service.CrawlingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Crawling", description = "크롤링용입니다 무시하세요")
public class CrawlingController {

        private final CrawlingService crawlingService;

        @Operation(summary = "무시하쇼 무시무시")
        @GetMapping(value = "/crawling")
        public void crawling() {
                crawlingService.saveWork();
        }

        @Operation(summary = "무시하쇼 무시무시")
        @GetMapping(value = "/writing")
        public void writing() {
                crawlingService.saveRatingFile();
        }

}

