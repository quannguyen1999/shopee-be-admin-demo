package com.shopee.ecommer.tests;


import com.shopee.ecommer.constants.AuthorityConstant;
import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.constants.ValueConstant;
import com.shopee.ecommer.controllers.rest.AccountRestController;
import com.shopee.ecommer.feignClient.AccountServerClient;
import com.shopee.ecommer.models.DocumentEnum;
import com.shopee.ecommer.models.RequestTestDto;
import com.shopee.ecommer.models.hateoas.AccountAssembler;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.requests.OtpRequestDto;
import com.shopee.ecommer.services.AccountService;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.tests.adapter.AdapterAccountTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.shopee.ecommer.constants.ValueConstant.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@ExtendWith({RestDocumentationExtension.class})
@WebMvcTest(AccountRestController.class)
public class AccountControllerTest extends AdapterAccountTest {
    @MockBean
    protected AccountService accountService;
    @MockBean
    protected ReportService reportService;
    @MockBean
    protected AccountAssembler accountAssembler;
    @MockBean
    protected AccountServerClient accountServerClient;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }

    @Test
    @DisplayName("Request get token.")
    void requestGetToken() throws Exception {
        //Init
        Oauth2ClientDto oauth2ClientDto = Oauth2ClientDto.builder()
                .grantType(AuthorityConstant.GRANT_TYPE_CUSTOM_PASSWORD)
                .userName(VALUE_USERNAME)
                .password(VALUE_PASSWORD).build();
        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.GET_TOKEN + handlerOauth2ClientDtoToParam(oauth2ClientDto);
        //Mock Fake Data
        Mockito.when(accountService.getToken(oauth2ClientDto)).thenReturn(writeTokenResponse());
        //Test and write doc
        requestMock().accept(RequestTestDto.builder().documentEnum(DocumentEnum.ACCOUNT_GET_TOKEN).build(), pathRequest);
    }

    @Test
    @DisplayName("Request Refresh token.")
    void requestGetRefreshToken() throws Exception {
        //Init
        Oauth2ClientDto oauth2ClientDto = Oauth2ClientDto.builder()
                .grantType(PARAM_REFRESH_TOKEN)
                .refreshToken(ValueConstant.DATA_FAKE).build();
        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.GET_REFRESH_TOKEN + handlerOauth2ClientDtoToParam(oauth2ClientDto);
        //Mock Fake Data
        Mockito.when(accountService.refreshToken(oauth2ClientDto)).thenReturn(writeTokenResponse());
        //Test and write doc
        requestMock().accept(RequestTestDto.builder().documentEnum(DocumentEnum.ACCOUNT_GET_REFRESH_TOKEN).build(), pathRequest);
    }

    @Test
    @DisplayName("Test Get Info")
    void getInfo() throws Exception {
        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.GET_INFO;
        //Mock Fake Data
        Mockito.when(accountService.getInfo(null)).thenReturn(buildAccountResponseDto());
        //Test and write doc
        requestMock().accept(RequestTestDto.builder().documentEnum(DocumentEnum.ACCOUNT_GET_INFO).build(), pathRequest);
    }

    @Test
    @DisplayName("Request Create Account.")
    void requestCreateAccount() {
        //Init
        AccountRequestDto accountRequestDto = buildAccountRequestCreateDto();
        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.CREATE;
        //Mock Fake Data
        Mockito.when(accountService.createAccount(accountRequestDto)).thenReturn(buildAccountResponseDto());
        //Test and write doc
        requestMock().accept(RequestTestDto.builder().documentEnum(DocumentEnum.ACCOUNT_CREATE).contentType(accountRequestDto).build(), pathRequest);
    }

    @Test
    @DisplayName("Request Registered Account.")
    void requestRegisteredAccount() {
        //Init
        AccountRequestDto accountRequestDto = buildAccountRequestRegisterDto();
        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.REGISTER;
        //Mock Fake Data
        Mockito.when(accountService.registeredAccount(accountRequestDto)).thenReturn(buildAccountResponseDto());
        //Test and write doc
        requestMock().accept(RequestTestDto.builder().documentEnum(DocumentEnum.ACCOUNT_REGISTER).contentType(accountRequestDto).build(), pathRequest);
    }

    @Test
    @DisplayName("Request Otp Account.")
    void requestOtpAccount() {
        //Init
        OtpRequestDto otpRequestDto = buildOtpRequestDto();
        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.OTP;
        //Mock Fake Data
        Mockito.when(accountService.verifyOtp(otpRequestDto)).thenReturn(buildAccountResponseDto());
        //Test and write doc
        requestMock().accept(RequestTestDto.builder().documentEnum(DocumentEnum.ACCOUNT_OTP).contentType(otpRequestDto).build(), pathRequest);
    }

}
