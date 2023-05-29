package com.oven.server.api.user.service;

import com.oven.server.api.jwt.JwtTokenProvider;
import com.oven.server.api.user.domain.InterestingWork;
import com.oven.server.api.user.domain.RatingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.request.UserInfoRequest;
import com.oven.server.api.user.dto.request.UserRequest;
import com.oven.server.api.user.dto.response.TokenResponse;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.dto.WorkListResponse;
import com.oven.server.api.work.dto.WorkResponse;
import com.oven.server.api.work.dto.response.GetWorkDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse register(JoinRequest joinRequest) {

        String resultPw = passwordEncoder.encode(joinRequest.getPassword());

        User user = User.builder().userName(joinRequest.getUserName()).nickname(joinRequest.getNickName())
                .password(resultPw).build();
        userRepository.save(user);

        return TokenResponse.builder().message("회원가입 성공").build();
    }

    @Transactional
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

    public List<GetWorkDto> getLikes(User user) {

        log.info(">>>>>>>>>>>>>" + user.getUsername());
        List<GetWorkDto> workList = new ArrayList<GetWorkDto>();


//        Claims claims;
//        claims = jwtTokenProvider.getClaims(userInfoRequest.getToken());
//        String userName = (String) claims.get("userName");
//        User user = userRepository.findByUserName(userName).get();

        List<InterestingWork> interestingWorks = user.getInterestingWorkList();

        for (int i = 0; i < interestingWorks.size(); i++) {
            Work work = interestingWorks.get(i).getWork();
            GetWorkDto getWorkDto = GetWorkDto.builder()
                    .title(work.getTitleKr()).poster(work.getPoster()).build();
            workList.add(getWorkDto);
        }

        return workList;

    }

    public List<GetWorkDto> getRatings(User user) {
        List<GetWorkDto> workList = new ArrayList<GetWorkDto>();

//        Claims claims;
//        claims = jwtTokenProvider.getClaims(userInfoRequest.getToken());
//        String userName = (String) claims.get("userName");
//        User user = userRepository.findByUserName(userName).get();

        List<RatingWork> ratingWorks = user.getRatingWorkList();

        for (int i = 0; i < ratingWorks.size(); i++) {
            Work work = ratingWorks.get(i).getWork();
            GetWorkDto getWorkDto = GetWorkDto.builder()
                    .title(work.getTitleKr()).poster(work.getPoster()).build();
            workList.add(getWorkDto);
        }

        return workList;

    }

}
