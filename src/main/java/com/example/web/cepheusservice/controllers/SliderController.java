package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.SliderDto;
import com.example.web.cepheusservice.domain.entity.SliderEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.mappers.impl.SliderMapper;
import com.example.web.cepheusservice.services.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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

    @GetMapping(path = "/admin/slider/{id}")
    private ResponseEntity<SliderEntity> getSliderById(@PathVariable Long id) {
        SliderEntity findSlider = sliderService.findSliderById(id).orElse(null);
        return ResponseEntity.ok(findSlider);
    }

    @PutMapping(path = "/admin/slider/{id}")
    private ResponseEntity<String> update(@PathVariable Long id, @RequestBody SliderEntity sliderEntity) {
        boolean exists = sliderService.isExists(id);
        if (!exists) return ResponseEntity.notFound().build();
        System.out.println(sliderEntity);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/admin/slider/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<String> patchUpdate(
            @PathVariable(value = "id", required = false) Long id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "link", required = false) String link,
            @RequestParam(value = "image", required = false) MultipartFile file
    ) throws IOException {
        if (!sliderService.isExists(id)) {
            return ResponseEntity.notFound().build();
        }

        SliderEntity sliderEntity = new SliderEntity();
        if (title!= null) {
            sliderEntity.setTitle(title);
        }
        if (text!= null) {
            sliderEntity.setText(text);
        }
        if (link!= null) {
            sliderEntity.setLink(link);
        }
        if (file!= null) {
            sliderEntity.setName(file.getName());
            sliderEntity.setBytes(file.getBytes());
            sliderEntity.setSize(file.getSize());
            sliderEntity.setContentType(file.getContentType());
            sliderEntity.setOriginalFileName(file.getOriginalFilename());
        }

        sliderService.pathUpdate(id, sliderEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/admin/slider/{id}")
    private ResponseEntity<String> delete(@PathVariable Long id) {
        sliderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
