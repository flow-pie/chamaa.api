package com.chamaa.config;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.sql.Connection;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.chamaa.repositories")
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            logger.info("✅ Database connection successful! URL: {}",
                    connection.getMetaData().getURL());
        } catch (Exception e) {
            logger.error("❌ Database connection failed: {}", e.getMessage());
        }
    }
}