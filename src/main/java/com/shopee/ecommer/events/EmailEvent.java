package com.shopee.ecommer.events;

import com.shopee.ecommer.models.requests.EmailDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailEvent extends ApplicationEvent {
    private final EmailDto emailDto;

    public EmailEvent(Object source, EmailDto emailDto) {
        super(source);
        this.emailDto = emailDto;
    }
}
