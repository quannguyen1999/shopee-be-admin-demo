package com.shopee.shopeebeadmindemo.events.listener;

import com.shopee.shopeebeadmindemo.exceptions.InternerServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountListener {

    @Async
    @Retryable(value = {InternerServerException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @EventListener
    public void accountEvent(String string) throws InterruptedException {
        //Test Asynchronous
        Thread.sleep(1000);
        log.info("Spring Event: accountEvent");
        log.info("event: " + string);
        throw new InternerServerException("Test Error");
    }

    @Recover
    private void handlerRetryFailure(InternerServerException exception) {
        log.info("Save log for last retry: " + exception.getMessage());
    }

}

