package com.oven.server.api.work.service;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.user.repository.UserRepository;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SpringToFlaskService {

    private final UserRepository userRepository;
    private final WorkRepository workRepository;

    public String springToFlask(User user) {
        String flaskApiUrl = "http://172.17.0.2:6000/spring";
        String userId = String.valueOf(user.getId());

        //HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //HTTP 요청 본문 설정
        HttpEntity<String> requestEntity = new HttpEntity<>(userId, headers);

        //RestTemplate를 사용하여 POST 요청 보내기
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000); // 읽기 타임아웃을 10초로 설정 (밀리초)
        factory.setConnectTimeout(5000); // 연결 타임아웃을 5초로 설정 (밀리초)
        RestTemplate restTemplate = new RestTemplate(factory);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(flaskApiUrl, requestEntity, String.class);
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String response = responseEntity.getBody();
            System.out.println("플라스크 서버 응답: " + response);
            return response;
        } else {
            System.out.println("HTTP 요청 실패 " + responseEntity.getStatusCodeValue());
            return responseEntity.getStatusCode().toString();
        }
    }

}
