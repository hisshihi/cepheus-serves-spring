package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.SliderDto;
import com.example.web.cepheusservice.domain.entity.SliderEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.mappers.impl.SliderMapper;
import com.example.web.cepheusservice.services.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/admin/slider")
@RequiredArgsConstructor
public class SliderController {

    private final SliderService sliderService;
    private final SliderMapper mapper;

    @PostMapping(path = "/admin/slider")
    ResponseEntity<String> saveSliderImage(@RequestParam("image") MultipartFile multipartFile,
                                           @RequestParam("title") String title,
                                           @RequestParam("text") String text) throws IOException {
        sliderService.saveImage(multipartFile, title, text);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/slider")
    private List<SliderDto> getAllSliders() {
        List<SliderEntity> sliderEntities = sliderService.findAll();
        return sliderEntities.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

    @DeleteMapping(path = "/admin/slider/{id}")
    private ResponseEntity<String> delete(@PathVariable Long id) {
        sliderService.delete(id);
        return ResponseEntity.notFound().build();
    }

}
