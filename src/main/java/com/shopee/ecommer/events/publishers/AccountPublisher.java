package com.shopee.ecommer.events.publishers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class AccountPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(String message) {
        log.info("send mail Async-Start");
        applicationEventPublisher.publishEvent(message);
        log.info("send mail Async-Continue Processing");
    }

}
