package com.oven.server.api.work.service;

import com.oven.server.api.work.dto.response.SearchResultResponse;
import com.oven.server.api.work.dto.response.WorkListDto;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchWorksService {

    private final WorkRepository workRepository;

    // 제목으로만 검색 가능
    public SearchResultResponse searchWork(int size, Long workId, String keyword) throws BaseException {

        List<WorkListDto> workList = workRepository.searchWork(size, workId, keyword)
                .stream()
                .map(
                        work -> WorkListDto.builder()
                                .workId(work.getId())
                                .title(work.getTitleKr())
                                .poster(work.getPoster())
                                .build()
                )
                .collect(Collectors.toList());

        boolean nextPage = false;

        if(workList.size() > size) {
            nextPage = true;
            workList.remove(size);
        }

        return SearchResultResponse.builder()
                .nextPage(nextPage)
                .workListDtos(workList)
                .build();
    }

}
