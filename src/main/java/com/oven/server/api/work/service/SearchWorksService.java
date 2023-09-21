package com.oven.server.api.work.service;

import com.oven.server.api.work.dto.GetWorkListDto;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchWorksService {

    private final WorkRepository workRepository;

    // 제목으로만 검색 가능
    public List<GetWorkListDto> searchWork(String keyword) throws BaseException {

        List<GetWorkListDto> workList = workRepository.findByTitleKrContaining(keyword)
                .stream()
                .map(
                        work -> GetWorkListDto.builder()
                                .workId(work.getId())
                                .title(work.getTitleKr())
                                .poster(work.getPoster())
                                .build()
                )
                .collect(Collectors.toList());

        return workList;
    }

}
