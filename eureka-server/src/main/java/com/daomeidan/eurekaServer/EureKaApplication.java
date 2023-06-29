package com.daomeidan.eurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EureKaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EureKaApplication.class, args);
    }
}
