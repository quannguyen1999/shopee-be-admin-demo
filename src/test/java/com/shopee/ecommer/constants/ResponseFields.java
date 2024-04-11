package com.shopee.ecommer.constants;

import com.shopee.ecommer.models.responses.TokenResponseDto;
import com.shopee.ecommer.utils.FunctionUtils;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ResponseFields {

    public static final FieldDescriptor[] responseFieldGetToken = {
            fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.accessToken)).description("AccessToken"),
            fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.refreshToken)).description("RefreshToken"),
            fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.tokenType)).description("tokenType"),
            fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.expiresIn)).description("Time expire")
    };



}
