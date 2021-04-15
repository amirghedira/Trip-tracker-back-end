package com.tp.triptrack.demo.controller;

import com.tp.triptrack.demo.entity.ApiToken;
import com.tp.triptrack.demo.service.AccommodationService;
import com.tp.triptrack.demo.service.ApiTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/accommodation")
public class AccommodationController {
    @Autowired
    AccommodationService accommodationService;
    @Autowired
    ApiTokenService apiTokenService ;
    @GetMapping(value = "/hotels/{budget}")
    public ResponseEntity<String> getHotels(@PathVariable int budget){
        return accommodationService.hotelsList(budget);
    }
    @GetMapping(value="token")
    public ApiToken generateToken(){
        return apiTokenService.generateApiToken();

    }
}
