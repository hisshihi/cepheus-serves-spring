package com.example.web.cepheusservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(path = "/basket")
    public ResponseEntity<String> basket() {
        return new ResponseEntity<>("This is basket page", HttpStatus.OK);
    }

}
