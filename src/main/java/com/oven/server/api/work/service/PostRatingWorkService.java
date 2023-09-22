package com.oven.server.api.work.service;

import com.oven.server.api.user.domain.InterestingWork;
import com.oven.server.api.user.domain.RatingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.RatingWorkRepository;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.request.PostRatingDto;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostRatingWorkService {

    private final WorkRepository workRepository;
    private final RatingWorkRepository ratingWorkRepository;

    public void postRatingWork(@AuthenticationPrincipal User user, Long workId, PostRatingDto postRatingDto) throws BaseException {

        userValidate(user);
        Work work = workValidate(workId);

        RatingWork ratingWork = ratingWorkRepository.findByUserAndWork(user, work);

        if(ratingWork == null) {
            RatingWork newRatingWork = new RatingWork(user, work, postRatingDto.getRating());
            ratingWorkRepository.save(newRatingWork);
        } else {
            ratingWorkRepository.delete(ratingWork);
        }

    }

    private void userValidate(User user) throws BaseException {
        if(user == null) {
            throw new BaseException(ResponseCode.USER_NOT_FOUND);
        }
    }

    private Work workValidate(Long workId) throws BaseException {
        return workRepository.findById(workId).orElseThrow(
                () -> new BaseException(ResponseCode.WORK_NOT_FOUND)
        );
    }

}
