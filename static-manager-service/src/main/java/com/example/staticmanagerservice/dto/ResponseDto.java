package com.example.staticmanagerservice.dto;

import com.example.staticmanagerservice.analysis.AnalysisEntity;
import com.example.staticmanagerservice.sonarproject.SonarProjectEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private AnalysisEntity analysisEntity;
    private SonarProjectEntity sonarProjectEntity;
}
