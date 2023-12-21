package com.shopee.shopeebeadmindemo.events.publishers;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(String message) {
        applicationEventPublisher.publishEvent(message);
    }

}
