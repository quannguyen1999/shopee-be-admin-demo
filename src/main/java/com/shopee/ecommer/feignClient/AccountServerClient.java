package com.shopee.ecommer.feignClient;

import com.shopee.ecommer.feignClient.fallBack.AccountFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account", url = "${custom.security.oauth2Url}", fallback = AccountFallback.class)
public interface AccountServerClient {

    @PostMapping(value = "/oauth2/token")
    Object getToken(@RequestParam("client_id") String clientId,
                    @RequestParam("client_secret") String clientSecret,
                    @RequestParam("code") String code,
                    @RequestParam("grant_type") String grantType,
                    @RequestParam("redirect_uri") String redirectUrl
    );

    @PostMapping(value = "/oauth2/token")
    Object getRefreshToken(@RequestParam("client_id") String clientId,
                           @RequestParam("client_secret") String clientSecret,
                           @RequestParam("refresh_token") String refreshToken,
                           @RequestParam("grant_type") String grantType
    ) throws Exception;

}
