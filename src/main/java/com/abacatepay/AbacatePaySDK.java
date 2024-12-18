package com.abacatepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AbacatePaySDK {
    public static void main(String[] args){
        SpringApplication.run(AbacatePaySDK.class, args);
    }
}
