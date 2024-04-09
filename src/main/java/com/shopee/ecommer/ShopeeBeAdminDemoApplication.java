package com.shopee.ecommer;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Backend Admin Rest Api Documentation",
                description = "Api for ADMIN",
                version = "v1",
                contact = @Contact(
                        name = "Quan Nguyen",
                        email = "nguyendanganhquan99@gmail.com",
                        url = "none"
                ),
                license = @License(
                        name = "Apache License, Version 2.0",
                        url = "http://www."
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "External Documentation",
                url = "http://"
        )
)
public class ShopeeBeAdminDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeBeAdminDemoApplication.class, args);
    }

}
