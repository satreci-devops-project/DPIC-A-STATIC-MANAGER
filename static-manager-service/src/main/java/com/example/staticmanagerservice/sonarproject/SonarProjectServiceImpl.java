package com.example.staticmanagerservice.sonarproject;

import com.example.staticmanagerservice.analysis.AnalysisRepository;
import com.example.staticmanagerservice.analysis.AnalysisEntity;
import com.example.staticmanagerservice.analysis.ComponentDto;
import com.example.staticmanagerservice.dto.ResponseDto;
import com.example.staticmanagerservice.dto.WebhookPayloadDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class SonarProjectServiceImpl implements SonarProjectService {
    AnalysisRepository analysisRepository;
    SonarProjectRepository sonarProjectRepository;
    Environment environment;

    @Autowired
    public SonarProjectServiceImpl(AnalysisRepository analysisRepository, SonarProjectRepository sonarProjectRepository, Environment environment) {
        this.analysisRepository = analysisRepository;
        this.sonarProjectRepository = sonarProjectRepository;
        this.environment = environment;
    }

    @Override
    public SonarProjectEntity createSonarProject(WebhookPayloadDto webhookPayloadDto) {
        SonarProjectEntity sonarProjectEntity = new SonarProjectEntity();
        BeanUtils.copyProperties(webhookPayloadDto.getProject(), sonarProjectEntity);
        sonarProjectEntity.setProjectId(UUID.randomUUID().toString());
        return sonarProjectRepository.save(sonarProjectEntity);
    }
}
