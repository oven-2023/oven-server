package com.oven.server.api.work.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oven.server.common.exception.BaseException;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.response.GetWorkListDto;
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

    public List<GetWorkListDto> getRecommendWorkList(String dataFromFlask) throws BaseException, JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        long[] numpArray = objectMapper.readValue(dataFromFlask, long[].class);
        List<Work> recommendations = new ArrayList<Work>();

        for (int i = 0; i < numpArray.length; i++) {
            recommendations.add(workRepository.findById(numpArray[i]).get());
        }

        List<GetWorkListDto> recommendWorkDtoList = recommendations
                .stream()
                .map(
                        recommendWork -> GetWorkListDto
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
