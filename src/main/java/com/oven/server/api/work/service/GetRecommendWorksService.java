package com.oven.server.api.work.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oven.server.api.user.domain.RatingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.RatingWorkRepository;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.response.WorkListDto;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetRecommendWorksService {

    private final WorkRepository workRepository;
    private final UserRepository userRepository;
    private final RatingWorkRepository ratingWorkRepository;

    public List<WorkListDto> getRecommendWorkList(User user) {

        user = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new BaseException(ResponseCode.USER_NOT_FOUND)
        );

        List<RatingWork> ratingWorks = ratingWorkRepository.findByUser(user);

        log.info("----ratingWorks: {}", ratingWorks.size());

        int max = 0;
        List<RatingWork> result = new ArrayList<>();

        for(User findUser : userRepository.findUsersByIdIsNot(user.getId())) {

            log.info("-----findUser: {}", findUser.getUsername());

            List<RatingWork> findRatingWorks = ratingWorkRepository.findByUser(findUser);
            List<RatingWork> tmp = findRatingWorks;
            List<RatingWork> sameWorks = new ArrayList<>();

            log.info("-----findRatingWorks: {}", findRatingWorks.size());

            int count = 0;

            for(int i = 0; i < ratingWorks.size(); i++) {
                for(int j = 0; j < findRatingWorks.size(); j++) {
                    if(ratingWorks.get(i).getWork().equals(findRatingWorks.get(j).getWork())) {
                        count++;
                        tmp.remove(findRatingWorks.get(j));
                        log.info("-----ratingWork: {}, findRatingWork: {}", ratingWorks.get(i).getWork().getId(),findRatingWorks.get(j).getWork().getId());
                    }
                }
            }

            log.info("-----count: {}", count);
            log.info("----tmp size: {}", tmp.size());

            if(count > max) {
                max = count;
                result = tmp;
            }
        }

        log.info("-----max: {}", max);

        List<Long> recommend = new ArrayList<>();

        for(RatingWork ratingWork : result) {
            recommend.add(ratingWork.getWork().getId());
        }

        return workRepository.findWorksByIdIn(recommend).stream()
                .map(
                        work -> WorkListDto.builder()
                                .workId(work.getId())
                                .title(work.getTitleKr())
                                .poster(work.getPoster())
                                .build()
                )
                .collect(Collectors.toList());

    }

//    public List<WorkListDto> getRecommendWorkList(String dataFromFlask) throws BaseException, JsonProcessingException {
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(dataFromFlask);
//        System.out.println(dataFromFlask.getClass().getName());
//
//        Map<String, String> dataMap = objectMapper.readValue(dataFromFlask, new TypeReference<Map<String, String>>() {});
//        String resultString = dataMap.get("result");
//
//        List<Long> longList = Arrays.stream(resultString.split(","))
//                .map(s -> s.replaceAll("[\\[\\]\\\\\"]", "")).map(String::trim)
//                .map(Long::parseLong)
//                .collect(Collectors.toList());
//
//        List<Work> recommendations = new ArrayList<Work>();
//
//        for (int i = 0; i < longList.size(); i++) {
//            recommendations.add(workRepository.findById(longList.get(i)).get());
//        }
//
//        List<WorkListDto> recommendWorkDtoList = recommendations
//                .stream()
//                .map(
//                        recommendWork -> WorkListDto
//                                .builder()
//                                .workId(recommendWork.getId())
//                                .title(recommendWork.getTitleKr())
//                                .poster(recommendWork.getPoster())
//                                .build()
//                )
//                .collect(Collectors.toList());
//
//        return recommendWorkDtoList;
//    }
}
