package com.shopee.ecommer.tests;


import static com.shopee.ecommer.constants.ResponseFields.responseFieldGetInfo;
import static com.shopee.ecommer.constants.ResponseFields.responseFieldGetToken;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;


import com.shopee.ecommer.constants.AuthorityConstant;
import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.constants.QueryParameters;
import com.shopee.ecommer.controllers.rest.AccountRestController;
import com.shopee.ecommer.models.CommonBaseModel;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.TestDto;
import com.shopee.ecommer.models.responses.TokenResponseDto;
import org.apache.tomcat.util.http.parser.Authorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith({ RestDocumentationExtension.class})
@WebMvcTest(AccountRestController.class)
public class AccountControllerTest extends AdapterCommonTest{

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
                .password(VALUE_PASSWORD)
                .build();

        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.GET_TOKEN + handlerOauth2ClientDtoToParam(oauth2ClientDto);

        //Mock Fake Data
        Mockito.when(accountService.getToken(oauth2ClientDto)).thenReturn(
                TokenResponseDto.builder()
                        .accessToken("xxx")
                        .refreshToken("xxx")
                        .expiresIn(3600)
                        .tokenType("Bearer")
                        .build()
        );

        //Test and write doc
        mockMvc.perform(post(pathRequest)
                ).andDo(document("accounts-get-token",
                        queryParameters(QueryParameters.searchQueryParamToken),
                        responseFields(responseFieldGetToken)
                ));
    }

    @Test
    @DisplayName("Test Get Info")
    void getInfo() throws Exception {
        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.GET_INFO;

        //Build response
        AccountResponseDto accountResponseDto = new  AccountResponseDto();
        accountResponseDto.setId(UUID.randomUUID());
        accountResponseDto.setUsername(VALUE_USERNAME);
        accountResponseDto.setBirthday(simpleDateFormat.format(currentDate));
        accountResponseDto.setGender(false);
        accountResponseDto.setEmail("admin@gmail.com");
        accountResponseDto.setMfaEnabled(true);
        accountResponseDto.setMfaRegistered(true);
        accountResponseDto.setIsActive(true);
        accountResponseDto.setTotalRecord(1);
        accountResponseDto.setCreated(currentDate);
        accountResponseDto.setUpdated(currentDate);
        accountResponseDto.setUserCreated(VALUE_USERNAME);
        accountResponseDto.setUserUpdated(VALUE_USERNAME);

        //Mock Fake Data
        Mockito.when(accountService.getInfo(null)).thenReturn(accountResponseDto);

        //Test and write doc
        mockMvc.perform(get(pathRequest).header(Authorization.class.getSimpleName(),"Bearer xxx"))
                .andDo(document("accounts-get-info",
                        responseFields(responseFieldGetInfo)
                ));
    }


//    @Test
//    @DisplayName("When getting a customer by id then return the.")
//            void givenId_whenGetCustomerById_thenReturnCustomer()
//            throws Exception {
//        Map<String, Object> crud = new HashMap<>();
//        crud.put("username", "Sample Model");
//
//
//        TestDto accountRequestDto = new TestDto();
//        accountRequestDto.setUsername(VALUE_USERNAME);
//
//        TestDto accountResponseDto = new TestDto();
//        accountRequestDto.setUsername(VALUE_USERNAME);
//        Mockito.when(accountService.test(accountRequestDto)).thenReturn(
//              accountResponseDto
//        );
//
//        mockMvc.perform(post(PathApi.ACCOUNT + PathApi.TEST)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                .content(ob.writeValueAsString(accountRequestDto))
//        )
//                .andDo(document("accounts",
//                        requestFields(
//                              customerResponseFields
//                        ),
//                        responseFields(
//                                fieldWithPath("id").description("Account ID").type(JsonFieldType.NULL),
//                                fieldWithPath("username").description("Username of the account").type(JsonFieldType.NULL),
//                                fieldWithPath("birthday").description("Birthday of the account").type(JsonFieldType.NULL),
//                                fieldWithPath("gender").description("Gender of the account").type(JsonFieldType.NULL),
//                                fieldWithPath("email").description("Email of the account").type(JsonFieldType.NULL),
//                                fieldWithPath("avatar").description("Avatar URL of the account").type(JsonFieldType.NULL),
//                                fieldWithPath("isActive").description("Indicates if the account is active").type(JsonFieldType.NULL),
//                                fieldWithPath("fromBirthday").description("From Birthday").type(JsonFieldType.NULL),
//                                fieldWithPath("toBirthday").description("To Birthday").type(JsonFieldType.NULL)
//                        )
//                        ));
//    }
//
//    private FieldDescriptor[] customerResponseFields = {
//            fieldWithPath("id").description("Account ID").ignored(),
//            fieldWithPath("username").description("Username of the account"),
//            fieldWithPath("birthday").description("Birthday of the account").ignored(),
//            fieldWithPath("gender").description("Gender of the account").ignored(),
//            fieldWithPath("email").description("Email of the account").ignored(),
//            fieldWithPath("avatar").description("Avatar URL of the account").ignored(),
//            fieldWithPath("isActive").description("Indicates if the account is active").ignored(),
//            fieldWithPath("fromBirthday").description("From Birthday").ignored(),
//            fieldWithPath("toBirthday").description("To Birthday").ignored(),
//
//
//    };
}
