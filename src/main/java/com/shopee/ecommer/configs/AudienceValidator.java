package com.shopee.ecommer.configs;

import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


@Service
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        System.out.println(token.getAudience().toString());
        return OAuth2TokenValidatorResult.success();
    }

  
}
