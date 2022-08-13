package com.by.ms.message.service.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableDiscoveryClient
@MapperScan(basePackages = {"com.by.ms.message.service.kernel.dao"})
@EntityScan(basePackages = {"com.by.ms.message.service"})
@ComponentScan(basePackages = {"com.by.ms.message.service"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
        "com.by.ms.message.apis.MessageApis"}) })
@EnableScheduling
@SpringBootApplication
public class MsMessageServiceWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMessageServiceWebApplication.class, args);
    }

}
