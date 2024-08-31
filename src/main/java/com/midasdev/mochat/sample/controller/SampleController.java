package com.midasdev.mochat.sample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/sample")
@RequiredArgsConstructor
public class SampleController {

    private final SampleSpringDataRepository sampleSpringDataRepository;

    @PostMapping
    public String createSampleData() {
        log.info("createSampleData");
        sampleSpringDataRepository.save(SampleEntity.getSample());
        return "Success";
    }
}
