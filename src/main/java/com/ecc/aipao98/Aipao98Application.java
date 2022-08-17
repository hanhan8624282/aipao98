package com.ecc.aipao98;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ecc.aipao98")
public class Aipao98Application {

    public static void main(String[] args) {
        SpringApplication.run(Aipao98Application.class, args);
    }

}
