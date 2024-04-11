package com.shopee.ecommer;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.controllers.rest.AccountRestController;
import com.shopee.ecommer.models.hateoas.AccountAssembler;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.TestDto;
import com.shopee.ecommer.services.AccountService;
import com.shopee.ecommer.services.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ExtendWith({ RestDocumentationExtension.class})
@WebMvcTest(AccountRestController.class)
public class AccountControllerTest {
    private MockMvc mockMvc;

    private static final ObjectMapper ob = new ObjectMapper();

    @MockBean
    private AccountService accountService;

    @MockBean
    private ReportService reportService;

    @MockBean
    private AccountAssembler accountAssembler;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
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
//                        requestFields(
//                              customerResponseFields
//                        ),
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
            fieldWithPath("username").description("username"),

    };
}
