package com.oven.server.api.work.service;

import com.oven.server.api.response.BaseException;
import com.oven.server.api.response.ResponseStatus;
import com.oven.server.api.user.domain.InterestingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.InterestingWorkRepository;
import com.oven.server.api.work.domain.WorkProvider;
import com.oven.server.api.work.dto.response.GetProviderDto;
import com.oven.server.api.work.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendProviderService {

    private final InterestingWorkRepository interestingWorkRepository;
    private final ProviderRepository providerRepository;

    public GetProviderDto recommendProvider(User user) throws BaseException {

        List<InterestingWork> interestingWorkList = interestingWorkRepository.findByUserId(user.getId());
        GetProviderDto getProviderDto;

        if(interestingWorkList.size() < 10) {
            throw new BaseException(ResponseStatus.NOT_ENOUGH_DATA);
        } else {
            int frequency[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};

            for(InterestingWork interestingWork : interestingWorkList) {
                List<WorkProvider> providerList = interestingWork.getWork().getProviderList();

                for(WorkProvider provider : providerList) {
                    frequency[provider.getProvider().getId().intValue()]++;
                }
            }

            int maxIndex = 0;

            for(int i = 1; i < 13; i++) {
                if(frequency[maxIndex] < frequency[i]) {
                    maxIndex = i;
                }
            }

            getProviderDto = GetProviderDto.builder()
                    .name(providerRepository.findById((long) maxIndex).get().getName())
                    .build();
        }

        return getProviderDto;

    }
}
