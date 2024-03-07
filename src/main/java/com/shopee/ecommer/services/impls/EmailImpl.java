package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.events.publishers.EmailPublisher;
import com.shopee.ecommer.feignClient.EmailServiceClient;
import com.shopee.ecommer.models.requests.EmailDto;
import com.shopee.ecommer.services.EmailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailImpl implements EmailService {
//    protected final EmailPublisher emailPublisher;

    protected final EmailServiceClient emailServiceClient;

    @Override
    public void sendMail(EmailDto emailDto) {
        System.out.println("testing");
//        emailServiceClient.sendMail();
    }
}
