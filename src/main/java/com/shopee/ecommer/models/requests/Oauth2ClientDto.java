package com.shopee.ecommer.models.requests;


import lombok.Data;

@Data
public class Oauth2ClientDto {

    private String code;

    private String redirectUrl;

    private String refreshToken;

}
