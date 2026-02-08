package com.chamaa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.chamaa.repositories")
public class DatabaseConfig {
    // Hibernate and HikariCP configuration is handled through application properties
    // This class serves as a marker for database configuration and future customization
}

