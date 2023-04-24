package com.example.staticmanagerservice.dto;

import com.example.staticmanagerservice.sonarproject.SonarProjectDto;
import lombok.Data;

@Data
public class WebhookPayloadDto {
    private String serverUrl;
    private String taskId;
    private String status;
    private SonarProjectDto project;
}
