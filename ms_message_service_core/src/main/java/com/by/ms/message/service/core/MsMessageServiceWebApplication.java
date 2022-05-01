package com.by.ms.message.service.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.by.ms.message.service.kernel.dao"})
@EntityScan(basePackages = {"com.by.ms.message.service"})
@ComponentScan(basePackages = {"com.by.ms.message.service"})
public class MsMessageServiceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMessageServiceWebApplication.class, args);
    }

}
