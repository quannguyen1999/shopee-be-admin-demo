package com.shopee.ecommer.constants;

import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import org.springframework.restdocs.request.ParameterDescriptor;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class QueryParameters {

    static ParameterDescriptor password =   parameterWithName(Oauth2ClientDto.Fields.password).description("Grant type like custom_password");
    static ParameterDescriptor userName =   parameterWithName(Oauth2ClientDto.Fields.userName).description("userName user");
    static ParameterDescriptor grantType =   parameterWithName(Oauth2ClientDto.Fields.grantType).description("grantType user");
    static  ParameterDescriptor refreshToken =   parameterWithName(Oauth2ClientDto.Fields.refreshToken).description("refreshToken user");

    public static final ParameterDescriptor[] searchQueryParamToken = {
            password, userName, grantType
    };

    public static final ParameterDescriptor[] searchQueryParamRefreshToken = {
            refreshToken, grantType
    };

}
