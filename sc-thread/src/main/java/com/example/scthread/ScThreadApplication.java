package com.example.scthread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ScThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScThreadApplication.class, args);
    }

}
