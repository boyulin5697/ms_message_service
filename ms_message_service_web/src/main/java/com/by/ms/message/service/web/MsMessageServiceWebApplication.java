package com.by.ms.message.service.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.by.ms.message.service")
@SpringBootApplication
public class MsMessageServiceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMessageServiceWebApplication.class, args);
    }

}
