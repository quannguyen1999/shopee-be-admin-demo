package com.shopee.ecommer.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopee.ecommer.models.hateoas.AccountAssembler;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.services.AccountService;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.utils.FunctionUtils;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

public class AdapterCommonTest {

    @MockBean
    protected AccountService accountService;

    @MockBean
    protected ReportService reportService;

    @MockBean
    protected AccountAssembler accountAssembler;

    MockMvc mockMvc;

    protected static final ObjectMapper ob = new ObjectMapper();

    String handlerOauth2ClientDtoToParam(Oauth2ClientDto oauth2ClientDto){
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
}
