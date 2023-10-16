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

    public void springToFlask(User user, String csvContent) {
        String flaskApiUrl = "http://43.201.68.242:5000/spring";
        String userId = String.valueOf(user.getId());

        //HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //HTTP 요청 본문 설정
        HttpEntity<String[]> requestEntity = new HttpEntity<>(new String[]{userId, csvContent}, headers);

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

    public String writing() {
        StringWriter csvData = new StringWriter();
        BufferedWriter bw = new BufferedWriter(csvData);
        String NEWLINE = System.lineSeparator();

        try {
            Random random = new Random();
            List<User> users = userRepository.findAll();

            bw.write("user_id, work_id, rating");
            bw.write(NEWLINE);

            for (int i = 0; i < users.size(); i++) {
                List<Work> randomWorks = workRepository.findRandoms();
                String userIdStr = String.valueOf(users.get(i).getId());
                for (int j = 0; j < randomWorks.size(); j++) {
                    String workIdStr = String.valueOf(randomWorks.get(j).getId());
                    String rating = String.valueOf(random.nextInt(4) + 2);
                    String totalstr = userIdStr + "," + workIdStr + "," + rating;
                    bw.write(totalstr);
                    bw.write("\n");
                }
            }

            bw.flush();
            String csvContent = csvData.toString();
            return csvContent;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(ResponseCode.FAIL_BAD_REQUEST);
        }
    }

}
