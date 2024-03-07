package com.shopee.ecommer.feignClient;

import com.shopee.ecommer.feignClient.fallBack.AccountFallback;
import com.shopee.ecommer.feignClient.fallBack.EmailFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "email", url = "${custom.email.url}", fallback = EmailFallback.class)
public interface EmailServiceClient {
    @GetMapping(value = "/email")
    void sendMail();

}
