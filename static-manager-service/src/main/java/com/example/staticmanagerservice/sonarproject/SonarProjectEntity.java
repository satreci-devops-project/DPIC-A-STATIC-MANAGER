package com.example.staticmanagerservice.sonarproject;

import com.example.staticmanagerservice.analysis.AnalysisEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "SONARQUBE_PROJECT")
public class SonarProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String projectId;
    @Column(nullable = false, unique = true)
    private String key;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;

    @OneToMany(mappedBy = "sonarProjectEntity")
    private List<AnalysisEntity> analysisEntityList = new ArrayList<>();

    public void addAnalysis(AnalysisEntity analysisEntity) {
        analysisEntity.setSonarProjectEntity(this);
    }
}
