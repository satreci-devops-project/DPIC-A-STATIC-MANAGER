package com.example.staticmanagerservice.analysis;

import com.example.staticmanagerservice.sonarproject.SonarProjectEntity;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "STATIC_ANALYSIS_RESULT")
public class AnalysisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String staticAnalysisResultId;
    @Column(nullable = false)
    private Integer lineOfCode;
    @Column(nullable = false)
    private Integer bugs;
    @Column(nullable = false)
    private Integer codeSmells;
    @Column(nullable = false)
    private Integer vulnerabilities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private SonarProjectEntity sonarProjectEntity;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;

    public void setSonarProject(SonarProjectEntity sonarProjectEntity) {
        if (Objects.nonNull(this.sonarProjectEntity)) {
            this.sonarProjectEntity.getAnalysisEntityList().remove(this);
        }
        this.sonarProjectEntity = sonarProjectEntity;
        sonarProjectEntity.getAnalysisEntityList().add(this);
    }
}
