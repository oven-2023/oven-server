package com.oven.server.api.work.service;

import com.oven.server.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SpringToFlaskService {

    public void springToFlask(User user) {
        String flaskApiUrl = "http://43.201.68.242:5000/spring";
        String userId = String.valueOf(user.getId());

        //HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //HTTP 요청 본문 설정
        HttpEntity<String> requestEntity = new HttpEntity<>(userId, headers);

        //RestTemplate를 사용하여 POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(flaskApiUrl, requestEntity, String.class);
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String response = responseEntity.getBody();
            System.out.println("플라스크 서버 응답: " + response);
        } else {
            System.out.println("HTTP 요청 실패 " + responseEntity.getStatusCodeValue());
        }
    }

}
