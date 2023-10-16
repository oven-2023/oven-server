package com.oven.server.api.user.service;

import com.oven.server.api.user.domain.RefreshToken;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.dto.request.IdCheckRequest;
import com.oven.server.api.user.dto.request.JoinRequest;
import com.oven.server.api.user.dto.request.LoginRequest;
import com.oven.server.api.user.dto.request.RefreshTokenRequest;
import com.oven.server.api.user.dto.response.AccessTokenResponse;
import com.oven.server.api.user.dto.response.IdCheckResponse;
import com.oven.server.api.user.dto.response.JwtTokenResponse;
import com.oven.server.api.user.repository.RefreshTokenRepository;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import com.oven.server.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

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

    public void logout(User user, RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenRequest.getRefreshToken()).orElseThrow(
                () -> new BaseException(ResponseCode.REFRESH_TOKEN_NOT_FOUND)
        );

        refreshTokenRepository.delete(refreshToken);
        SecurityContextHolder.clearContext();

    }

    public AccessTokenResponse reissueAccessToken(RefreshTokenRequest refreshTokenRequest) {

        if(!jwtTokenProvider.validateToken(refreshTokenRequest.getRefreshToken())) {
            throw new BaseException(ResponseCode.TOKEN_NOT_VALID);
        }
        if(!refreshTokenRepository.existsById(refreshTokenRequest.getRefreshToken())) {
            throw new BaseException(ResponseCode.REFRESH_TOKEN_NOT_FOUND);
        }

        log.info("[reissueAccessToken] 액세스 토큰 재발급 시작");

        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenRequest.getRefreshToken()).orElseThrow(
                () -> new BaseException(ResponseCode.REFRESH_TOKEN_NOT_FOUND)
        );

        User user = userRepository.findByUsername(refreshToken.getUsername()).orElseThrow(
                () -> new BaseException(ResponseCode.USER_NOT_FOUND)
        );

        AccessTokenResponse accessTokenResponse = AccessTokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getUsername(), new Date()))
                .build();

        log.info("[reissueAccessToken] 액세스 토큰 재발급 완료: {}", accessTokenResponse.getAccessToken());

        return accessTokenResponse;

    }

}
