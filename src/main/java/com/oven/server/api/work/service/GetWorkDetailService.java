package com.oven.server.api.work.service;

import com.oven.server.api.user.domain.InterestingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.InterestingWorkRepository;
import com.oven.server.api.user.repository.RatingWorkRepository;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.response.ProviderDto;
import com.oven.server.api.work.dto.response.WorkDetailDto;
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
    private final InterestingWorkRepository interestingWorkRepository;
    private final RatingWorkRepository ratingWorkRepository;

    public WorkDetailDto getWorkDetail(User user, Long workId) throws BaseException {

        Work findWork = workRepository.findById(workId).orElseThrow(() -> new BaseException(ResponseCode.WORK_NOT_FOUND));
        WorkDetailDto workDetailDto = WorkDetailDto.builder()
                .titleKr(findWork.getTitleKr())
                .titleEng(findWork.getTitleEng())
                .poster(findWork.getPoster())
                .actor(findWork.getActor())
                .summary(findWork.getSummary())
                .ageRating(findWork.getAgeRating())
                .year(findWork.getYear())
                .director(findWork.getDirector())
                .providerList(workProviderRepository.findWorkProviderByWorkId(workId)
                        .stream()
                        .map(
                                provider -> ProviderDto
                                        .builder()
                                        .name(provider.getProvider().getName())
                                        .build()
                        )
                        .collect(Collectors.toList())
                )
                .genre(findWork.getGenre().toString())
                .liked(interestingWorkRepository.findByUserAndWork(user, findWork) != null)
                .rating((ratingWorkRepository.findByUserAndWork(user, findWork) != null) ?
                        ratingWorkRepository.findByUserAndWork(user, findWork).getRating() : null)
                .build();

        return workDetailDto;

    }

}
