package com.oven.server.api.user.service;

import com.oven.server.api.jwt.JwtTokenProvider;
import com.oven.server.api.response.BaseException;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.request.UserRequest;
import com.oven.server.api.user.dto.response.TokenResponse;
import com.oven.server.api.user.repository.InterestingWorkRepository;
import com.oven.server.api.user.repository.RatingWorkRepository;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.response.GetWorkDto;
import com.oven.server.api.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final WorkRepository workRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final InterestingWorkRepository interestingWorkRepository;
    private final RatingWorkRepository ratingWorkRepository;


    public void register(JoinRequest joinRequest) throws BaseException {

        String resultPw = passwordEncoder.encode(joinRequest.getPassword());

        User user = User.builder()
                .userName(joinRequest.getUserName())
                .nickname(joinRequest.getNickName())
                .password(resultPw)
                .build();

        userRepository.save(user);

    }

    public TokenResponse doLogin(UserRequest userRequest) {

        User user = userRepository.findByUserName(userRequest.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        String token = jwtTokenProvider.createToken(user.getUsername());

        return TokenResponse.builder()
                .message("로그인 성공").token(token).build();

    }

    @Transactional(readOnly = true)
    public List<GetWorkDto> getLikes(User user) {

        log.info(">>>>>>>>>>>>> " + user.getUsername());

        List<GetWorkDto> interestingWorkDtoList = interestingWorkRepository.findByUserId(user.getId())
                .stream()
                .map(
                        interestingWork -> GetWorkDto.builder()
                                .workId(interestingWork.getWork().getId())
                                .poster(interestingWork.getWork().getPoster())
                                .title(interestingWork.getWork().getTitleKr())
                                .build()
                )
                .collect(Collectors.toList());

        return interestingWorkDtoList;

    }

    @Transactional(readOnly = true)
    public List<GetWorkDto> getRatings(User user) {

        List<GetWorkDto> ratingWorkDtoList = ratingWorkRepository.findByUserId(user.getId())
                .stream()
                .map(
                        ratingWork -> GetWorkDto.builder()
                                .workId(ratingWork.getWork().getId())
                                .poster(ratingWork.getWork().getPoster())
                                .title(ratingWork.getWork().getTitleKr())
                                .build()
                )
                .collect(Collectors.toList());

        return ratingWorkDtoList;

    }

}
