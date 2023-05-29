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
public class GetPopularWorkListService {

    public final WorkRepository workRepository;

    public List<GetWorkDto> getPopularWorkList() throws BaseException {

        // 일단 크롤링 한 대로 추천 순위 상위 10개 가져옴
        List<GetWorkDto> popularWorkDtoList = workRepository.findTop10ByOrderByIdAsc()
                .stream()
                .map(
                        popularWork -> GetWorkDto.builder()
                                .workId(popularWork.getId())
                                .title(popularWork.getTitleKr())
                                .poster(popularWork.getPoster())
                                .build()
                )
                .collect(Collectors.toList());

        return popularWorkDtoList;

    }

}
