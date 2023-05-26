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
public class SearchWorkService {

    private final WorkRepository workRepository;

    // 제목으로만 검색 가능
    public List<GetWorkDto> searchWork(String keyword) throws BaseException {

            List<GetWorkDto> searchedWorkList = workRepository.findByTitleKrContaining(keyword)
                    .stream()
                    .map(
                            searchedWork -> GetWorkDto.builder()
                                    .workId(searchedWork.getId())
                                    .title(searchedWork.getTitleKr())
                                    .poster(searchedWork.getPoster())
                                    .build()
                    )
                    .collect(Collectors.toList());

            return searchedWorkList;
    }

}
