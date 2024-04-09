package com.shopee.ecommer.events;

import org.springframework.context.ApplicationEvent;

public class AccountEvent extends ApplicationEvent {
    private final String message;

    public AccountEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}