package com.example.staticmanagerservice.sonarproject;

import com.example.staticmanagerservice.dto.ResponseDto;
import com.example.staticmanagerservice.dto.WebhookPayloadDto;

public interface SonarProjectService {
    SonarProjectEntity createSonarProject(WebhookPayloadDto webhookPayloadDto);
}
