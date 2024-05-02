package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.entity.SliderEntity;
import com.example.web.cepheusservice.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/slider")
@RequiredArgsConstructor
public class SliderImageController {

    private final ImageService imageService;

    @PostMapping
    ResponseEntity<String> saveSliderImage(@RequestParam("image") MultipartFile multipartFile,
                                           @RequestParam("title") String title,
                                           @RequestParam("text") String text) throws IOException {
        SliderEntity sliderEntity = imageService.saveImage(multipartFile, title, text);
        return ResponseEntity.ok().build();
    }

}
