package com.leon.testspringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableBatchProcessing
public class TestSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringBatchApplication.class, args);
    }
}
