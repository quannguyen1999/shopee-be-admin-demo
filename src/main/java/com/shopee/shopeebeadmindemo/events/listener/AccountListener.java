package com.shopee.shopeebeadmindemo.events.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Slf4j
@EnableAsync
@Component
public class AccountListener {

    @Async
    @EventListener
    public void accountEvent(String string) throws InterruptedException {
        //Test Asynchronous
        Thread.sleep(10000);
        log.info("Spring Event: accountEvent");
        log.info("event: " + string);
    }

}

