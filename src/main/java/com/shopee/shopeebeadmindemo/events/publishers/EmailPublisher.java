package com.shopee.shopeebeadmindemo.events.publishers;

import com.shopee.shopeebeadmindemo.events.EmailEvent;
import com.shopee.shopeebeadmindemo.models.requests.EmailDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class EmailPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(EmailDto emailDto) {
        log.info("send mail Async-Start");
        EmailEvent emailEvent = new EmailEvent(this, emailDto);
        applicationEventPublisher.publishEvent(emailEvent);
        log.info("send mail Async-Continue Processing");
    }

}
