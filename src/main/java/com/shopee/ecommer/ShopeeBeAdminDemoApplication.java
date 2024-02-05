package com.shopee.ecommer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopeeBeAdminDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeBeAdminDemoApplication.class, args);
    }

}
