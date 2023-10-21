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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetRecommendWorksService {

    private final WorkRepository workRepository;

    public List<WorkListDto> getRecommendWorkList(String dataFromFlask) throws BaseException, JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
//        String[] stringArray = objectMapper.readValue(dataFromFlask, String[].class);
        List<String> stringList = objectMapper.readValue(dataFromFlask, new TypeReference<List<String>>() {});
        long[] longArray = stringList.stream().mapToLong(Long::parseLong).toArray();

        List<Work> recommendations = new ArrayList<Work>();

        for (int i = 0; i < longArray.length; i++) {
            recommendations.add(workRepository.findById(longArray[i]).get());
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
