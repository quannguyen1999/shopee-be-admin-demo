package com.shopee.ecommer.constants;

import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class QueryParameters {
    public static final ParameterDescriptor[] searchQueryParamToken = {
            parameterWithName(Oauth2ClientDto.Fields.password).description("Password user"),
            parameterWithName(Oauth2ClientDto.Fields.userName).description("Username of user"),
            parameterWithName(Oauth2ClientDto.Fields.grantType).description("Grant type like custom_password"),
    };

}
