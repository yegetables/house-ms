package com.xupt.house;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableCaching
@EnableScheduling
@MapperScan("com.xupt.house.mapper*")
@SpringBootApplication
public class HouseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HouseApplication.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");
        log.info("SENS started at http://localhost:" + serverPort);
    }

}
