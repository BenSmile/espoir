package com.bkafirongo.devsecopsdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class NumericController {

    private static final String baseURL = "http://node-service:5000/plusone";

    RestTemplate restTemplate = new RestTemplate();

    @RestController
    public class compare {

        @GetMapping("/")
        public String welcome() {
            return "Kubernetes DevSecOps";
        }

        @GetMapping("/compare/{value}")
        public String compareToFifty(@PathVariable int value) {
            String message;
            if (value > 50) {
                message = "Greater than 50";
            } else {
                message = "Smaller than or equal to 50";
            }
            return message;
        }

        @GetMapping("/increment/{value}")
        public int increment(@PathVariable int value) {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseURL + '/' + value, String.class);
            String response = responseEntity.getBody();
            log.info("Value Received in Request - {}", value);
            log.info("Node Service Response - {}", response);
            return Integer.parseInt(response);
        }
    }

}