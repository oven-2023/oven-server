package com.oven.server.api.work.service;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.work.dto.response.GetWorkDto;
import com.oven.server.api.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetRecommendWorksService {

    private final WorkRepository workRepository;

    public List<GetWorkDto> getRecommendWorkList() throws BaseException {

        List<GetWorkDto> recommendWorkDtoList = workRepository.findTop10ByOrderByIdAsc()
                .stream()
                .map(
                        recommendWork -> GetWorkDto
                                .builder()
                                .title(recommendWork.getTitleKr())
                                .poster(recommendWork.getPoster())
                                .build()
                )
                .collect(Collectors.toList());

        return recommendWorkDtoList;
    }
}
