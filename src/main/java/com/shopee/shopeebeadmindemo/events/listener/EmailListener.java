package com.shopee.shopeebeadmindemo.events.listener;

import com.shopee.shopeebeadmindemo.events.EmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Slf4j
@EnableAsync
@Component
public class EmailListener {

    @Async
    @EventListener
    public void emailEvent(EmailEvent event) throws InterruptedException {
        //Test Asynchronous
        Thread.sleep(10000);
        log.info("Spring Event: event");
        log.info("finished: " + event.getEmailDto());
    }

}
