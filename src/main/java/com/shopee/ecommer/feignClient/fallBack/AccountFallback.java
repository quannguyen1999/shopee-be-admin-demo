package com.shopee.ecommer.feignClient.fallBack;

import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.exceptions.InternerServerException;
import com.shopee.ecommer.feignClient.AccountServerClient;
import org.springframework.stereotype.Component;


@Component
public class AccountFallback implements AccountServerClient {
    @Override
    public Object getToken(String clientId, String clientSecret, String code, String grantType, String redirectUrl) {
        throw new InternerServerException(MessageErrors.SERVER_ACCOUNT_UNAVAILABLE.toString());
    }

    @Override
    public Object getRefreshToken(String clientId, String clientSecret, String refreshToken, String grantType) throws Exception {
        throw new InternerServerException(MessageErrors.SERVER_ACCOUNT_UNAVAILABLE.toString());
    }
}
