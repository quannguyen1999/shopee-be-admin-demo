package com.shopee.ecommer.tests.adapter;

import com.shopee.ecommer.constants.ValueConstant;
import com.shopee.ecommer.models.RequestTestDto;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.requests.OtpRequestDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.TokenResponseDto;
import com.shopee.ecommer.utils.FunctionUtils;
import feign.Request;
import org.apache.logging.log4j.util.Strings;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;
import java.util.function.BiConsumer;

import static com.shopee.ecommer.constants.ValueConstant.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

public class AdapterAccountTest {
    protected MockMvc mockMvc;

    protected String handlerOauth2ClientDtoToParam(Oauth2ClientDto oauth2ClientDto) {
        StringBuilder result = new StringBuilder();
        result.append(FunctionUtils.convertFieldToString().apply(Oauth2ClientDto.Fields.code, oauth2ClientDto.getCode()));
        result.append(FunctionUtils.convertFieldToString().apply(Oauth2ClientDto.Fields.grantType, oauth2ClientDto.getGrantType()));
        result.append(FunctionUtils.convertFieldToString().apply(Oauth2ClientDto.Fields.password, oauth2ClientDto.getPassword()));
        result.append(FunctionUtils.convertFieldToString().apply(Oauth2ClientDto.Fields.code, oauth2ClientDto.getCode()));
        result.append(FunctionUtils.convertFieldToString().apply(Oauth2ClientDto.Fields.redirectUrl, oauth2ClientDto.getRedirectUrl()));
        result.append(FunctionUtils.convertFieldToString().apply(Oauth2ClientDto.Fields.refreshToken, oauth2ClientDto.getRefreshToken()));
        result.append(FunctionUtils.convertFieldToString().apply(Oauth2ClientDto.Fields.userName, oauth2ClientDto.getUserName()));
        return StringUtils.isBlank(result.toString()) ? Strings.EMPTY : "?" + result;
    }

    protected TokenResponseDto writeTokenResponse() {
        return TokenResponseDto.builder()
                .accessToken(ValueConstant.DATA_FAKE)
                .refreshToken(ValueConstant.DATA_FAKE)
                .expiresIn(3600)
                .tokenType(ValueConstant.DATA_FAKE)
                .build();
    }

    protected AccountResponseDto buildAccountResponseDto() {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setId(UUID.randomUUID());
        accountResponseDto.setUsername(VALUE_USERNAME);
        accountResponseDto.setBirthday(SIMPLE_DATE_FORMAT.format(CURRENT_DATE));
        accountResponseDto.setGender(false);
        accountResponseDto.setEmail("admin@gmail.com");
        accountResponseDto.setMfaEnabled(true);
        accountResponseDto.setMfaRegistered(true);
        accountResponseDto.setIsActive(true);
        accountResponseDto.setTotalRecord(1);
        accountResponseDto.setCreated(CURRENT_DATE);
        accountResponseDto.setUpdated(CURRENT_DATE);
        accountResponseDto.setUserCreated(VALUE_USERNAME);
        accountResponseDto.setUserUpdated(VALUE_USERNAME);
        return accountResponseDto;
    }

    protected AccountRequestDto buildAccountRequestCreateDto() {
        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setUsername(VALUE_USERNAME);
        accountRequestDto.setBirthday(new Date());
        accountRequestDto.setGender(false);
        accountRequestDto.setEmail("admin@gmail.com");
        accountRequestDto.setAvatar(DATA_FAKE);
        return accountRequestDto;
    }

    protected AccountRequestDto buildAccountRequestRegisterDto() {
        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setUsername(VALUE_PHONE);
        return accountRequestDto;
    }

    protected OtpRequestDto buildOtpRequestDto() {
        OtpRequestDto otpRequestDto = new OtpRequestDto();
        otpRequestDto.setUsername(VALUE_PHONE);
        otpRequestDto.setValue("1111112");
        return otpRequestDto;
    }

    public BiConsumer<RequestTestDto, String> requestMock() {
        return (input, url) -> {
            try {
                MockHttpServletRequestBuilder mockHttpServletRequestBuilder;
                if (!ObjectUtils.isEmpty(input.getContentType())) {
                    mockHttpServletRequestBuilder = post(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(OBJECT_MAPPER.writeValueAsString(input.getContentType()));
                } else {
                    mockHttpServletRequestBuilder = input.documentEnum.httpMethod.equals(Request.HttpMethod.POST)
                            ? post(url) : get(url);
                }

                ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

                if (!ObjectUtils.isEmpty(input.documentEnum.parameterDescriptors)) {
                    resultActions.andDo(document(input.documentEnum.toString(),
                            queryParameters(input.documentEnum.parameterDescriptors),
                            responseFields(input.documentEnum.getFieldDescriptors()
                            )
                    ));
                } else if (!ObjectUtils.isEmpty(input.documentEnum.requestField)) {
                    resultActions.andDo(document(input.documentEnum.toString(),
                            requestFields(input.documentEnum.requestField),
                            responseFields(input.documentEnum.getFieldDescriptors()
                            )
                    ));
                } else {
                    resultActions.andDo(document(input.documentEnum.toString(),
                            responseFields(input.documentEnum.getFieldDescriptors())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
