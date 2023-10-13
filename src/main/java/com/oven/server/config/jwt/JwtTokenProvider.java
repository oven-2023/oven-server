package com.oven.server.config.jwt;

import com.oven.server.api.user.dto.response.JwtTokenResponse;
import com.oven.server.api.user.service.RefreshTokenService;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.secret}")
    private String secretKey;
    private final long tokenValidTime = 1000L * 60 * 60;    // 액세스 토큰 유효 시간 60분
    private final long refreshValidTime = 1000L * 60 * 60 * 24 * 14;    // 리프레쉬 토큰 유효 시간 2주

    @PostConstruct
    protected void init() {
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    public JwtTokenResponse createJwtToken(String username) {
        log.info("[createJwtToken] Jwt 토큰 생성 시작");

        final Date now = new Date();
        log.info("Date 생성 Date : {} ", now);

        JwtTokenResponse jwtTokenResponse = JwtTokenResponse.builder()
                .accessToken(createAccessToken(username, now))
                .refreshToken(createRefreshToken(username, now))
                .build();

        log.info("[createJwtToken] Jwt 토큰 생성 완료");


        return jwtTokenResponse;
    }

    public String createAccessToken(String username, Date now) {
        log.info("[createAccessToken] 액세스 토큰 생성 시작");

        Claims claims = Jwts.claims().setSubject(username);
        log.info("Access Token에서 claims 생성 claims : {}", claims);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuer("Oven")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("[createToken] 액세스 토큰 생성 완료");

        return accessToken;
    }

    public String createRefreshToken(String username, Date now) {
        log.info("[createRefreshToken] 리프레쉬 토큰 생성 시작");

        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("Oven")
                .setExpiration(new Date(now.getTime() + refreshValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        refreshTokenService.saveRefreshToken(refreshToken, username);

        log.info("[createRefreshToken] 리프레쉬 토큰 생성 완료");

        return refreshToken;
    }

    public Authentication getAuthentication(String token) {
        log.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserEmail(token));

        log.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails User Email : {}", userDetails.getUsername());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserEmail(String token) {
        log.info("[getUserEmail] 토큰 기반 회원 구별 정보 추출");

        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        log.info("[getUserEmail] 토큰 기반 회원 구별 정보 추출 완료, username : {}", username);

        return username;
    }

    public String resolveToken(HttpServletRequest request) {
        log.info("[resolveToken] HTTP 헤더에서 Token 값 추출");

        String authorization = request.getHeader("Authorization");

        if(authorization != null) {
            String token = authorization.split(" ")[1].trim();
            log.info("[resolveToken] HTTP 헤더에서 Token 값 추출 완료: {}", token);
            return token;
        }

        return null;
    }

    public boolean validateToken(String token) {
        log.info("[validateToken] 토큰 유효 체크 시작");

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (BaseException e) {
            throw new BaseException(ResponseCode.TOKEN_NOT_VALID);
        }

    }

}
