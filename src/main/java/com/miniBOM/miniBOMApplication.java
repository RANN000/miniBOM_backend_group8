package com.miniBOM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.huawei.innovation","com.miniBOM"})
public class miniBOMApplication {
    public static void main(String[] args) {
        SpringApplication.run(miniBOMApplication.class, args);
    }
}
