package com.oven.server.api.security;

import com.oven.server.api.config.StringUtil;
import com.oven.server.api.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends GenericFilterBean {
//
//    private final JwtTokenProvider jwtTokenProvider;
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 헤더에서 JWT 를 받아옵니다.
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//        // 유효한 토큰인지 확인합니다.
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            // SecurityContext 에 Authentication 객체를 저장합니다.
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println("토큰 유효함");
//        }
//        chain.doFilter(request, response);
//    }
//
//}

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenProvider jwtTokenProvider;
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!hasAuthorizationBearer(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = jwtTokenProvider.resolveToken(servletRequest);
        log.info("[doFilterInternal] token 값 추출 완료: token : {}", token);
        log.info("[doFilterInternal] token 값 유효성 체크 시작");
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("[doFilterInternal] token 값 유효성 체크 완료");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        log.info("[hasAuthorizationBearer] Authorization: Bearer {AccessToken} 형태인지 검증");
        String header = request.getHeader("Authorization");
        if (StringUtil.isNullOrEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
        return true;
    }
}