package com.oven.server.api.work.service;

import com.oven.server.api.user.domain.InterestingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.InterestingWorkRepository;
import com.oven.server.api.work.domain.Work;
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
public class PostInterestingWorkService {

    private final WorkRepository workRepository;
    private final InterestingWorkRepository interestingWorkRepository;

    public void postInterestingWork(@AuthenticationPrincipal User user, Long workId) throws BaseException {

        userValidate(user);
        Work work = workValidate(workId);

        InterestingWork interestingWork = interestingWorkRepository.findByUserAndWork(user, work);

        if(interestingWork == null) {
            InterestingWork newInterestingWork = new InterestingWork(user, work);
            interestingWorkRepository.save(newInterestingWork);
        } else {
            interestingWorkRepository.delete(interestingWork);
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
