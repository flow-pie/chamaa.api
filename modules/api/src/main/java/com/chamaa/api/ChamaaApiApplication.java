package com.chamaa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.chamaa.api", "com.chamaa.core", "com.chamaa.blockchain"})
public class ChamaaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChamaaApiApplication.class, args);
    }
}
