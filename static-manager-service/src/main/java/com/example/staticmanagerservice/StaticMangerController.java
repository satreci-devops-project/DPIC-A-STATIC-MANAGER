package com.example.staticmanagerservice;

import com.example.staticmanagerservice.analysis.AnalysisEntity;
import com.example.staticmanagerservice.analysis.AnalysisService;
import com.example.staticmanagerservice.dto.ResponseDto;
import com.example.staticmanagerservice.dto.WebhookPayloadDto;
import com.example.staticmanagerservice.sonarproject.SonarProjectEntity;
import com.example.staticmanagerservice.sonarproject.SonarProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class StaticMangerController {
    private AnalysisService analysisService;
    private SonarProjectService sonarProjectService;

    @Autowired
    public StaticMangerController(AnalysisService analysisService, SonarProjectService sonarProjectService) {
        this.analysisService = analysisService;
        this.sonarProjectService = sonarProjectService;
    }

    @PostMapping(value = "/sonarqube-webhook/")
    public ResponseEntity<ResponseDto> createAnalysis(@RequestBody WebhookPayloadDto webhookPayloadDto) {
        SonarProjectEntity sonarProject = sonarProjectService.createSonarProject(webhookPayloadDto);
        AnalysisEntity analysis = analysisService.createAnalysis(webhookPayloadDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSonarProjectEntity(sonarProject);
        responseDto.setAnalysisEntity(analysis);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


}
