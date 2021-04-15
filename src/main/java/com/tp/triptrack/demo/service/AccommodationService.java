package com.tp.triptrack.demo.service;

import com.tp.triptrack.demo.entity.ApiToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class AccommodationService {
    @Autowired
    ApiTokenService apiTokenService;
    public ResponseEntity<String> hotelsList(int budget){
        //api uri
        String hotelsApiUri = "https://test.api.amadeus.com/v2/shopping/hotel-offers?cityCode=LON&priceRange=0-"
                                +budget+"&currency=GBP";
        //
        RestTemplate restTemplate = new RestTemplate();
        // http header config
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ApiToken apiToken = apiTokenService.generateApiToken();
        headers.set("Authorization", "Bearer "+apiToken.access_token);
        headers.set("Content-Type", "application/x-www-form-urlencoded ");

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> hotelList= restTemplate.exchange(hotelsApiUri, HttpMethod.GET, entity,String.class);
        return hotelList ;

    }
}
