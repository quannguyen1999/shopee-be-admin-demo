package com.shopee.ecommer;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.shopee.ecommer.controllers.rest.AccountRestController;
import com.shopee.ecommer.models.hateoas.AccountAssembler;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
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
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith({ RestDocumentationExtension.class})
@WebMvcTest(AccountRestController.class)
public class AccountControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private ReportService reportService;

    @MockBean
    private AccountAssembler accountAssembler;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("When getting a customer by id then return the.")
            void givenId_whenGetCustomerById_thenReturnCustomer()
            throws Exception {
        final var id = 1L;

        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setUsername("admin");

        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountRequestDto.setUsername("admin");
        Mockito.when(accountService.createAccount(accountRequestDto)).thenReturn(
              accountResponseDto
        );

        mockMvc.perform(post("/accounts/test")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("customer-get-by-id"));


    }
}
