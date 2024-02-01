package com.example.web.cepheusservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping(path = "/basket")
    public ResponseEntity<String> basket() {
        return new ResponseEntity<>("This is basket page", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/admin")
    public ResponseEntity<String> adminPage() {
        return new ResponseEntity<>("Admin page", HttpStatus.OK);
    }

}
