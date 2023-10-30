package com.oven.server.api.work.service;

import com.oven.server.api.user.domain.InterestingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.InterestingWorkRepository;
import com.oven.server.api.work.domain.Provider;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.domain.WorkProvider;
import com.oven.server.api.work.dto.response.ProviderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetRecommendProviderService {

    private final InterestingWorkRepository interestingWorkRepository;

    public ProviderResponse getRecommendProvider(User user) {

        List<Provider> providers = interestingWorkRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(interestingWork -> interestingWork.getWork().getProviderList())
                .map(workProviders -> workProviders.stream().map(WorkProvider::getProvider).collect(Collectors.toList()))
                .collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());

        Map<Provider, Long> providerCounts = providers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        ProviderResponse result = ProviderResponse.builder()
                .providerId(providerCounts.entrySet().stream()
                        .max(Map.Entry.comparingByValue()).map(providerLongEntry -> providerLongEntry.getKey().getId())
                        .orElse(null))
                .build();

        return result;

    }

}
