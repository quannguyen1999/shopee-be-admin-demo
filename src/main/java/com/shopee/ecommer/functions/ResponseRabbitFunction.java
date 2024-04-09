package com.shopee.ecommer.functions;

import com.shopee.ecommer.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class ResponseRabbitFunction {

    @Bean
    public Consumer<String> updateCommunication(AccountService accountsService) {
        return accountNumber -> {
            log.info("Received event from rabbit mq: " + accountNumber);
        };
    }

}
