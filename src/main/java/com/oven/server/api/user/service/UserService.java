package com.oven.server.api.user.service;

import com.oven.server.api.jwt.JwtTokenProvider;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.JoinRequest;
import com.oven.server.api.user.dto.TokenResponse;
import com.oven.server.api.user.dto.UserRequest;
import com.oven.server.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        String token = jwtTokenProvider.createToken(user.getUserName());

        return TokenResponse.builder()
                .message("로그인 성공").token(token).build();

    }

}
