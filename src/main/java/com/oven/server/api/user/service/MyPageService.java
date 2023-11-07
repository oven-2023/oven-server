package com.oven.server.api.user.service;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.InterestingWorkRepository;
import com.oven.server.api.user.repository.RatingWorkRepository;
import com.oven.server.api.work.dto.response.WorkListDto;
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
    public List<WorkListDto> getLikes(User user) throws BaseException {

        List<WorkListDto> interestingWorkDtoList = interestingWorkRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(
                        interestingWork -> WorkListDto.builder()
                                .workId(interestingWork.getWork().getId())
                                .poster(interestingWork.getWork().getPoster())
                                .title(interestingWork.getWork().getTitleKr())
                                .build()
                )
                .collect(Collectors.toList());

        return interestingWorkDtoList;

    }

    @Transactional(readOnly = true)
    public List<WorkListDto> getRatings(User user) {

        List<WorkListDto> ratingWorkDtoList = ratingWorkRepository.findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(
                        ratingWork -> WorkListDto.builder()
                                .workId(ratingWork.getWork().getId())
                                .poster(ratingWork.getWork().getPoster())
                                .title(ratingWork.getWork().getTitleKr())
                                .build()
                )
                .collect(Collectors.toList());

        return ratingWorkDtoList;

    }

}
