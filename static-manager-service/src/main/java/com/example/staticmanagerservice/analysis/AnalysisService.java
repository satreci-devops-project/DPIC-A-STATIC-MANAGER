package com.example.staticmanagerservice.analysis;

import com.example.staticmanagerservice.dto.ResponseDto;
import com.example.staticmanagerservice.dto.WebhookPayloadDto;

public interface AnalysisService {
    AnalysisEntity createAnalysis(WebhookPayloadDto webhookPayloadDto);
}
