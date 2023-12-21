package com.shopee.shopeebeadmindemo.events;

import com.shopee.shopeebeadmindemo.models.requests.EmailDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AccountEvent extends ApplicationEvent {
    private final EmailDto emailDto;

    public AccountEvent(Object source, EmailDto emailDto) {
        super(source);
        this.emailDto = emailDto;
    }
}