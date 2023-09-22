package com.oven.server.api.user.service;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.InterestingWorkRepository;
import com.oven.server.api.user.repository.RatingWorkRepository;
import com.oven.server.api.work.dto.GetWorkListDto;
import com.oven.server.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    private final InterestingWorkRepository interestingWorkRepository;
    private final RatingWorkRepository ratingWorkRepository;

    @Transactional(readOnly = true)
    public List<GetWorkListDto> getLikes(User user) throws BaseException {

        List<GetWorkListDto> interestingWorkDtoList = interestingWorkRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(
                        interestingWork -> GetWorkListDto.builder()
                                .workId(interestingWork.getWork().getId())
                                .poster(interestingWork.getWork().getPoster())
                                .title(interestingWork.getWork().getTitleKr())
                                .build()
                )
                .sorted()
                .collect(Collectors.toList());

        return interestingWorkDtoList;

    }

    @Transactional(readOnly = true)
    public List<GetWorkListDto> getRatings(User user) {

        List<GetWorkListDto> ratingWorkDtoList = ratingWorkRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(
                        ratingWork -> GetWorkListDto.builder()
                                .workId(ratingWork.getWork().getId())
                                .poster(ratingWork.getWork().getPoster())
                                .title(ratingWork.getWork().getTitleKr())
                                .build()
                )
                .collect(Collectors.toList());

        return ratingWorkDtoList;

    }

}
