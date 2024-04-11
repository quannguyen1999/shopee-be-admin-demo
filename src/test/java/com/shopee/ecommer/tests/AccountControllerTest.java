package com.shopee.ecommer.tests;


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
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.responses.TestDto;
import com.shopee.ecommer.models.responses.TokenResponseDto;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

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
                .userName("admin")
                .password("Password")
                .build();

        //Uri
        String pathRequest = PathApi.ACCOUNT + PathApi.GET_TOKEN + handlerOauth2ClientDtoToParam(oauth2ClientDto);

        //Mock Fake Data
        Mockito.when(accountService.getToken(oauth2ClientDto)).thenReturn(
                TokenResponseDto.builder()
                        .accessToken("eyJraWQiOiIyNjViOWQwYy1kYzg1LTRiODktYTNjYS05NzhkYTRjNDBhNGYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImFkbWluIiwibmJmIjoxNzEyODEzODg2LCJUZXN0IjoiVGVzdCBBY2Nlc3MgVG9rZW4iLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwNzAiLCJleHAiOjE3MTI4MTc0ODYsImlhdCI6MTcxMjgxMzg4NiwidXNlciI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiQURNSU4iXX0.iEEZ-eSYah_Z6je-7wvJ7tIA-vUSke1luHyQxp5Dc4FdxYJIcjW5OrAe6bdqzpC630KfpjBiMMsr2hUD6aEcHTZ23BTQhONuk61lcqKyw7-yGDc_pHQwv4GZIOwGY3xk8BiSH8cRub9eQsZ2W-hJrnh26_RseGqz7-eo8O6a8aAAy_ubb3HqkMfBercnW8xT6Od3R7PPSEluQ-POsgWHamPgMh4aftmvPdagEAcVOmT9_poOrj44cxUrjIHe1TD5XSq_bcuTlFwPEhhUtqvejkqgn68JD8M6bm71ilu23NrfnMOqAor_NYDfs-EklwbhGK1FIcxAl_TutOfvYOEMug")
                        .refreshToken("YmwPvrsTeBRE07j3iiRmMVkdRrndQyG7f0zL1eV93SbF1AZA_28xlj12FCvc34AMea53ynoiKJwx4dQBNj-nesO6gNifCQte6lMnHxaFsUU1kJYg3jD7tzoDLn2Spit0")
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
    @DisplayName("When getting a customer by id then return the.")
            void givenId_whenGetCustomerById_thenReturnCustomer()
            throws Exception {
        Map<String, Object> crud = new HashMap<>();
        crud.put("username", "Sample Model");


        TestDto accountRequestDto = new TestDto();
        accountRequestDto.setUsername("admin");

        TestDto accountResponseDto = new TestDto();
        accountRequestDto.setUsername("admin");
        Mockito.when(accountService.test(accountRequestDto)).thenReturn(
              accountResponseDto
        );

        mockMvc.perform(post(PathApi.ACCOUNT + PathApi.TEST)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(ob.writeValueAsString(accountRequestDto))
        )
                .andDo(document("accounts",
                        requestFields(
                              customerResponseFields
                        ),
                        responseFields(
                                fieldWithPath("id").description("Account ID").type(JsonFieldType.NULL),
                                fieldWithPath("username").description("Username of the account").type(JsonFieldType.NULL),
                                fieldWithPath("birthday").description("Birthday of the account").type(JsonFieldType.NULL),
                                fieldWithPath("gender").description("Gender of the account").type(JsonFieldType.NULL),
                                fieldWithPath("email").description("Email of the account").type(JsonFieldType.NULL),
                                fieldWithPath("avatar").description("Avatar URL of the account").type(JsonFieldType.NULL),
                                fieldWithPath("isActive").description("Indicates if the account is active").type(JsonFieldType.NULL),
                                fieldWithPath("fromBirthday").description("From Birthday").type(JsonFieldType.NULL),
                                fieldWithPath("toBirthday").description("To Birthday").type(JsonFieldType.NULL)
                        )
                        ));
    }

    private FieldDescriptor[] customerResponseFields = {
            fieldWithPath("id").description("Account ID").ignored(),
            fieldWithPath("username").description("Username of the account"),
            fieldWithPath("birthday").description("Birthday of the account").ignored(),
            fieldWithPath("gender").description("Gender of the account").ignored(),
            fieldWithPath("email").description("Email of the account").ignored(),
            fieldWithPath("avatar").description("Avatar URL of the account").ignored(),
            fieldWithPath("isActive").description("Indicates if the account is active").ignored(),
            fieldWithPath("fromBirthday").description("From Birthday").ignored(),
            fieldWithPath("toBirthday").description("To Birthday").ignored(),


    };
}
