package com.shopee.ecommer.constants;

import com.shopee.ecommer.models.CommonBaseModel;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.TokenResponseDto;
import com.shopee.ecommer.utils.FunctionUtils;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

public class ResponseFields {
    //Define for Common
    static FieldDescriptor created =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(CommonBaseModel.Fields.created)).description("Info of Created");
    static FieldDescriptor updated =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(CommonBaseModel.Fields.updated)).description("Info of Updated");
    static FieldDescriptor userCreated =  fieldWithPath(CommonBaseModel.Fields.userCreated).description("Info of userCreated");
    static FieldDescriptor userUpdated =  fieldWithPath(CommonBaseModel.Fields.userUpdated).description("Info of userUpdated");
    static FieldDescriptor page =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(CommonBaseModel.Fields.page)).description("Info of page");
    static FieldDescriptor size =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(CommonBaseModel.Fields.size)).description("Info of size");
    static FieldDescriptor totalRecord =  fieldWithPath(CommonBaseModel.Fields.totalRecord).description("Info of totalRecord");

    static FieldDescriptor accessToken =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.accessToken)).description("AccessToken");
    static FieldDescriptor refreshToken =    fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.refreshToken)).description("Info ofRefreshToken");
    static FieldDescriptor tokenType =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.tokenType)).description("Info of tokenType");
    static FieldDescriptor expiresIn =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(TokenResponseDto.Fields.expiresIn)).description("Time expire");

    //Define for account
    static FieldDescriptor id =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(AccountResponseDto.Fields.id)).description("id");
    static FieldDescriptor username =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(AccountResponseDto.Fields.username)).description("username");
    static FieldDescriptor birthday =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(AccountResponseDto.Fields.birthday)).description("birthday");
    static FieldDescriptor gender =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(AccountResponseDto.Fields.gender)).description("gender");
    static FieldDescriptor email =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(AccountResponseDto.Fields.email)).description("email");
    static FieldDescriptor avatar =  fieldWithPath(FunctionUtils.camelCaseToSnakeCase().apply(AccountResponseDto.Fields.avatar)).description("avatar");
    static FieldDescriptor isActive =  fieldWithPath(AccountResponseDto.Fields.isActive).description("isActive");
    static FieldDescriptor mfaEnabled =  fieldWithPath(AccountResponseDto.Fields.mfaEnabled).description("mfaEnabled");
    static FieldDescriptor mfaRegistered =  fieldWithPath(AccountResponseDto.Fields.mfaRegistered).description("mfaRegistered");

    public static final FieldDescriptor[] responseFieldGetToken = {
            accessToken, refreshToken, tokenType, expiresIn
    };

    public static final FieldDescriptor[] responseFieldGetInfo = {
            id, username, birthday, gender, email, avatar, isActive, mfaEnabled, mfaRegistered, created, updated,
            userCreated.ignored(), userUpdated.ignored(), page.ignored(), size.ignored(), totalRecord.ignored()
    };



}
