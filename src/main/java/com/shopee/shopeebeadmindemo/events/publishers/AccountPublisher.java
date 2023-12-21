package com.shopee.shopeebeadmindemo.events.publishers;

import com.shopee.shopeebeadmindemo.events.AccountEvent;
import com.shopee.shopeebeadmindemo.models.requests.EmailDto;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AccountPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(EmailDto emailDto) {
        AccountEvent accountEvent = new AccountEvent(this, emailDto);
        applicationEventPublisher.publishEvent(accountEvent);
    }

}
