package com.oven.server.api.work.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oven.server.common.exception.BaseException;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.response.WorkListDto;
import com.oven.server.api.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetRecommendWorksService {

    private final WorkRepository workRepository;

    public List<WorkListDto> getRecommendWorkList(String dataFromFlask) throws BaseException, JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(dataFromFlask);
        System.out.println(dataFromFlask.getClass().getName());

        Map<String, String> dataMap = objectMapper.readValue(dataFromFlask, new TypeReference<Map<String, String>>() {});
        String resultString = dataMap.get("result");

        List<Long> longList = Arrays.stream(resultString.split(","))
                .map(s -> s.replaceAll("[\\[\\]\\\\\"]", "")).map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Work> recommendations = new ArrayList<Work>();

        for (int i = 0; i < longList.size(); i++) {
            recommendations.add(workRepository.findById(longList.get(i)).get());
        }

        List<WorkListDto> recommendWorkDtoList = recommendations
                .stream()
                .map(
                        recommendWork -> WorkListDto
                                .builder()
                                .workId(recommendWork.getId())
                                .title(recommendWork.getTitleKr())
                                .poster(recommendWork.getPoster())
                                .build()
                )
                .collect(Collectors.toList());

        return recommendWorkDtoList;
    }
}
