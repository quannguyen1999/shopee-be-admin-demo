package com.shopee.ecommer.feignClient.fallBack;

import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.exceptions.InternerServerException;
import com.shopee.ecommer.feignClient.AccountServerClient;
import com.shopee.ecommer.feignClient.EmailServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailFallback implements EmailServiceClient {
    @Override
    public void sendMail() {
        log.info("error working email");
        throw new InternerServerException(MessageErrors.SERVER_ACCOUNT_UNAVAILABLE.toString());
    }

}
