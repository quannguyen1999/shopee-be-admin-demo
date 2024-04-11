package com.shopee.ecommer.models.requests;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Builder
public class Oauth2ClientDto {

    private String code;

    private String redirectUrl;

    private String refreshToken;

    private String grantType;

    private String userName;

    private String password;

}
