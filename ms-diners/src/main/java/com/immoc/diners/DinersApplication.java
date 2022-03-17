package com.immoc.diners;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.immoc.diners")
public class DinersApplication {
    public static void main(String[] args) {
        SpringApplication.run(DinersApplication.class,args);
    }
}
