package com.example.staticmanagerservice.sonarproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SonarProjectRepository extends JpaRepository<SonarProjectEntity, Long> {
    SonarProjectEntity findByKey(String key);
}
