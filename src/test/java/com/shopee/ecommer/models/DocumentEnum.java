package com.shopee.ecommer.models;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;

import static com.shopee.ecommer.constants.QueryParameters.searchQueryParamRefreshToken;
import static com.shopee.ecommer.constants.QueryParameters.searchQueryParamToken;
import static com.shopee.ecommer.constants.ResponseFields.*;

@AllArgsConstructor
@Getter
public enum DocumentEnum {
    ACCOUNT_GET_TOKEN(null, searchQueryParamToken, responseFieldGetToken, Request.HttpMethod.POST),
    ACCOUNT_GET_REFRESH_TOKEN(null, searchQueryParamRefreshToken, responseFieldGetToken, Request.HttpMethod.POST),
    ACCOUNT_GET_INFO(null, null, responseFieldGetInfo, Request.HttpMethod.GET),
    ACCOUNT_CREATE(requestFieldCreateAccount, null, responseFieldGetInfo, Request.HttpMethod.POST),
    ACCOUNT_REGISTER(requestFieldCreateAccount, null, responseFieldGetInfo, Request.HttpMethod.POST),
    ACCOUNT_OTP(requestFieldCreateAccount, null, responseFieldGetInfo, Request.HttpMethod.POST);

    public final FieldDescriptor[] requestField;

    public final ParameterDescriptor[] parameterDescriptors;

    public final FieldDescriptor[] fieldDescriptors;

    public final Request.HttpMethod httpMethod;

//    public final FieldDescriptorp[] fieldDescriptorps;


}
