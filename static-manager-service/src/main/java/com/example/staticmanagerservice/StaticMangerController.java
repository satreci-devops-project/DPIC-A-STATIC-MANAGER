package com.example.staticmanagerservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class StaticMangerController {

//    @PostMapping("/sonarqube-webhook")
//    @GetMapping("/sonarqube-webhook")
    @PostMapping(value = "/sonarqube-webhook/")
    public String test() {
        log.info("test");
        return "test";
    }


}
