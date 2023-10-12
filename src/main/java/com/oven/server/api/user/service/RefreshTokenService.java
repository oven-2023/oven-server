package com.oven.server.api.user.service;

import com.oven.server.api.user.domain.RefreshToken;
import com.oven.server.api.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String token, String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(token)
                .username(username)
                .build();

        refreshTokenRepository.save(refreshToken);

        log.info("[로그인 후 Refresh Token 저장]");
    }

}
