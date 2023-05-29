package com.oven.server.api.user.service;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.user.domain.RatingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.request.RatingWorkDto;
import com.oven.server.api.user.repository.RatingWorkRepository;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RatingWorkService {

    private final RatingWorkRepository ratingWorkRepository;
    private final WorkRepository workRepository;

    public void postRatingWork(User user, Long workId, RatingWorkDto ratingWorkDto) throws BaseException {

        userValidate(user);
        Work work = workValidate(workId);

        RatingWork ratingWork = ratingWorkRepository.findByUserIdAndWorkId(user.getId(), workId);

        if(ratingWork == null) {
            RatingWork newRatingWork = new RatingWork(user, work, ratingWorkDto.getRating());
            ratingWorkRepository.save(newRatingWork);
        } else {
            ratingWork.changeRating(ratingWorkDto.getRating());
        }

    }

    private void userValidate(User user) throws BaseException {
        if(user == null) {
            throw new BaseException(ResponseStatus.USER_NOT_FOUND);
        }
    }

    private Work workValidate(Long workId) throws BaseException {
        return workRepository.findById(workId).orElseThrow(() -> new BaseException(ResponseStatus.WORK_NOT_FOUND));
    }
}
