package com.oven.server.api.user.service;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.request.IdCheckRequest;
import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.request.LoginRequest;
import com.oven.server.api.user.dto.response.IdCheckResponse;
import com.oven.server.api.user.dto.response.JwtTokenResponse;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import com.oven.server.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public IdCheckResponse idDuplicateCheck(IdCheckRequest idCheckRequest) throws BaseException {

        return IdCheckResponse.builder()
                .idExists(userRepository.existsByUsername(idCheckRequest.getUsername()))
                .build();

    }

    public void join(JoinRequest joinRequest) throws BaseException {

        User newUser = User.builder()
                .username(joinRequest.getUsername())
                .nickname(joinRequest.getNickname())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .build();

        userRepository.save(newUser);

    }

    public JwtTokenResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BaseException(ResponseCode.ID_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BaseException(ResponseCode.PASSWORD_NOT_MATCH);
        }

        return jwtTokenProvider.createJwtToken(user.getUsername());
    }

}
