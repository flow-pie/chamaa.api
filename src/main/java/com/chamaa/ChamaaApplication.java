package com.chamaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chamaa")
public class ChamaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChamaaApplication.class, args);
    }
}
