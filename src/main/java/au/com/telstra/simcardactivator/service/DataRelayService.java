package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.pojo.ActivateUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;
import java.util.HashMap;


@Service
public class DataRelayService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // Constructor
    public DataRelayService(ObjectMapper objectMapper, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
        this.objectMapper = objectMapper;
    }

    public String relayToAnotherService(String jsonData) throws JsonProcessingException {
        String targetUrl = "http://localhost:8444/actuate";

        // Step 1: 解析收到的json数据
        ActivateUser user = objectMapper.readValue(jsonData, ActivateUser.class);

        // Step 2: 提取第一个记录的iccid
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("iccid", user.getIccid());
        String jsonString = objectMapper.writeValueAsString(jsonMap);

        // Step 3: post请求另一个服务
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

        // Step 4: 使用 RestTemplate 发送 post 请求给目标微服务
        ResponseEntity<String> response = restTemplate.exchange(targetUrl, HttpMethod.POST, entity, String.class);

        // 返回目标服务器的响应
        return response.getBody();
    }
}
