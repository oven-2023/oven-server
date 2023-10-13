package com.oven.server.config.jwt;

import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.oven.server.config.jwt.JwtExceptionHandler.handle;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(request);
        log.info("[doFilterInternal] token 값 추출 완료: token : {}", token);

        log.info("[doFilterInternal] token 값 유효성 체크 시작");

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("[doFilterInternal] token 값 유효성 체크 완료");
            }
        } catch (BaseException e) {
            log.info("[doFilterInternal] token 값 유효성 체크 실패");
            handle(response, ResponseCode.TOKEN_NOT_VALID);
        }

        filterChain.doFilter(request, response);
    }
}
