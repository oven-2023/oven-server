package com.oven.server.api.work.service;


import com.oven.server.api.work.dto.response.WorkListDto;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPopularWorkListService {

    public final WorkRepository workRepository;

    public List<WorkListDto> getPopularWorkList() throws BaseException {

        // 일단 크롤링 한 대로 추천 순위 상위 10개 가져옴
        List<WorkListDto> popularWorkDtoList = workRepository.findTop10ByOrderByIdAsc()
                .stream()
                .map(
                        popularWork -> WorkListDto.builder()
                                .workId(popularWork.getId())
                                .title(popularWork.getTitleKr())
                                .poster(popularWork.getPoster())
                                .build()
                )
                .collect(Collectors.toList());

        return popularWorkDtoList;

    }

}
