package com.shopee.ecommer.models.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Oauth2AuthorizationDto {

    @JsonAlias(value = "grant_type")
    private String grantType;

    @JsonAlias(value = "client_id")
    private String clientId;

    @JsonAlias(value = "client_secret")
    private String clientSecret;

    @JsonAlias(value = "code")
    private String code;

    @JsonAlias(value = "state")
    private String state;

    @JsonAlias(value = "redirect_uri")
    private String redirectUri;

}
