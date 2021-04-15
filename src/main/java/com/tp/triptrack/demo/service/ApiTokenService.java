package com.tp.triptrack.demo.service;

import com.tp.triptrack.demo.entity.ApiToken;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ApiTokenService {
    public ApiToken generateApiToken(){
        //auth credentials
        String grantType="client_credentials";
        String clientId = "NYkZNGZVRZX9YNEclcESkA3ISoFzLIqp";
        String clientSecret = "6r8Ow67vgAggLjDe";
        //
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("grant_type", grantType);
        postParameters.add("client_id",clientId);
        postParameters.add("client_secret",clientSecret);
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
        //
        RestTemplate restTemplate = new RestTemplate();
        ApiToken apiToken = restTemplate.postForObject("https://test.api.amadeus.com/v1/security/oauth2/token",
                                                        postParameters,
                                                        ApiToken.class);
        return apiToken ;
    }
}
