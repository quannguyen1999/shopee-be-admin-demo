package com.shopee.ecommer.services;

import com.shopee.ecommer.models.requests.EmailDto;

public interface EmailService {
    void sendMail(EmailDto emailDto);
}
