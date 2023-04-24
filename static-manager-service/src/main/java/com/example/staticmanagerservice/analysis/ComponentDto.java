package com.example.staticmanagerservice.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ComponentDto {
    private Integer lineOfCode;
    private Integer bugs;
    private Integer codeSmells;
    private Integer vulnerabilities;

    @SuppressWarnings("unchecked")
    @JsonProperty("component")
    private void unpackNested(Map<String,Object> component) {
        System.out.println(component.get("key"));
        List<Map<String, Object>> measures = (List<Map<String, Object>>) component.get("measures");
        measures.forEach(measure -> {
            String metricKey = (String) measure.get("metric");
            String value = (String) measure.get("value");
            switch (metricKey) {
                case "ncloc":
                    this.lineOfCode = Integer.valueOf(value);
                    break;
                case "code_smells":
                    this.codeSmells = Integer.valueOf(value);
                    break;
                case "bugs":
                    this.bugs = Integer.valueOf(value);
                    break;
                case "new_vulnerabilities":
                    Map<String, Object> period = (Map<String, Object>) measure.get("period");
                    this.vulnerabilities = Integer.valueOf((String) period.get("value"));
                    break;
                default:
                    break;
            }
        });
    }
}
