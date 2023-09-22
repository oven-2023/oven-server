package com.oven.server.api.work.service;

import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.response.GetProviderDto;
import com.oven.server.api.work.dto.response.GetWorkDetailDto;
import com.oven.server.api.work.repository.WorkProviderRepository;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetWorkDetailService {

    private final WorkRepository workRepository;
    private final WorkProviderRepository workProviderRepository;

    public GetWorkDetailDto getWorkDetail(Long workId) throws BaseException {

        Work findWork = workRepository.findById(workId).orElseThrow(() -> new BaseException(ResponseCode.WORK_NOT_FOUND));

        GetWorkDetailDto workDetailDto = GetWorkDetailDto.builder()
                .titleKr(findWork.getTitleKr())
                .titleEng(findWork.getTitleEng())
                .poster(findWork.getPoster())
                .actor(findWork.getActor())
                .summary(findWork.getSummary())
                .rating(findWork.getRating())
                .year(findWork.getYear())
                .director(findWork.getDirector())
                .providerList(workProviderRepository.findWorkProviderByWorkId(workId)
                        .stream()
                        .map(
                                provider -> GetProviderDto
                                        .builder()
                                        .name(provider.getProvider().getName())
                                        .build()
                        )
                        .collect(Collectors.toList())
                )
                .genre(findWork.getGenre().toString())
                .build();

        return workDetailDto;
    }

}
