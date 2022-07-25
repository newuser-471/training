package com.example.search.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping("/weather/search")
    @HystrixCommand(fallbackMethod = "fallbackDetails")
    public ResponseEntity<?> getDetails() {
        //TODO
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }

    public ResponseEntity<?> fallbackDetails(){
        return new ResponseEntity<>("this is fall back method", HttpStatus.OK);
    }
}
