package com.shopee.shopeebeadmindemo.events.publishers;

import com.shopee.shopeebeadmindemo.events.EmailEvent;
import com.shopee.shopeebeadmindemo.models.requests.EmailDto;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmailPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(EmailDto emailDto) {
        EmailEvent emailEvent = new EmailEvent(this, emailDto);
        applicationEventPublisher.publishEvent(emailEvent);
    }

}
