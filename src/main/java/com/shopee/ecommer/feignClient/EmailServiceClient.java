package com.shopee.ecommer.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "emailServiceClient", url = "${custom.email.url}")
public interface EmailServiceClient {
    @GetMapping(value = "/email")
    void sendMail();

}
