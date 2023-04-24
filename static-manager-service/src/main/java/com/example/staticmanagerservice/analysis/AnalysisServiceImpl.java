package com.example.staticmanagerservice.analysis;

import com.example.staticmanagerservice.sonarproject.SonarProjectRepository;
import com.example.staticmanagerservice.dto.ResponseDto;
import com.example.staticmanagerservice.dto.WebhookPayloadDto;
import com.example.staticmanagerservice.sonarproject.SonarProjectEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    AnalysisRepository analysisRepository;
    SonarProjectRepository sonarProjectRepository;
    Environment environment;

    @Autowired
    public AnalysisServiceImpl(AnalysisRepository analysisRepository, SonarProjectRepository sonarProjectRepository, Environment environment) {
        this.analysisRepository = analysisRepository;
        this.sonarProjectRepository = sonarProjectRepository;
        this.environment = environment;
    }

    @Override
    public AnalysisEntity createAnalysis(WebhookPayloadDto webhookPayloadDto) {
        WebClient webClient = WebClient.builder()
                .baseUrl(webhookPayloadDto.getServerUrl())
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(environment.getProperty("sonar.usertoken"), ""))
                .build();

        String url = UriComponentsBuilder.fromUriString(environment.getProperty("sonar.apiurl"))
                .queryParam("component", webhookPayloadDto.getProject().getKey())
                .queryParam("metricKeys", environment.getProperty("sonar.metrickeys"))
                .toUriString();

        Mono<ComponentDto> responseMono = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ComponentDto.class);
        ComponentDto componentDto = responseMono.block();

        AnalysisEntity analysisEntity = new AnalysisEntity();
        BeanUtils.copyProperties(componentDto, analysisEntity);
        analysisEntity.setStaticAnalysisResultId(UUID.randomUUID().toString());
        SonarProjectEntity sonarProjectEntity = sonarProjectRepository.findByKey(webhookPayloadDto.getProject().getKey());
        sonarProjectEntity.addAnalysis(analysisEntity);
        return analysisRepository.save(analysisEntity);
    }
}
