package com.ulbs.careerstartup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ulbs.careerstartup.mapper")
public class CareerStartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerStartupApplication.class, args);
    }

}
