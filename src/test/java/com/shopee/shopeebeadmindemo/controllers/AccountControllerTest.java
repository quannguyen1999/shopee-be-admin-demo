package com.shopee.shopeebeadmindemo.controllers;

import com.shopee.shopeebeadmindemo.config.MockSecurityConfig;
import com.shopee.shopeebeadmindemo.constants.PathApi;
import com.shopee.shopeebeadmindemo.controllers.rest.AccountRestController;
import com.shopee.shopeebeadmindemo.models.hateoas.AccountAssembler;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.services.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// AccountRestControllerTest.java
@RunWith(SpringRunner.class)
@WebMvcTest(AccountRestController.class)
@AutoConfigureMockMvc
@Import(MockSecurityConfig.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private ReportService reportService;

    @MockBean
    private AccountAssembler accountAssembler;

    @Test
    public void getAccountList() throws Exception {
        mockMvc.perform(post(PathApi.ACCOUNT + PathApi.LIST).contentType(MediaType.APPLICATION_JSON).content("{\"page\":0,\"size\":2}"))
                .andExpect(status().isOk())
                .andDo(document(PathApi.ACCOUNT + PathApi.LIST,
                        requestFields(fieldWithPath(CommonPageInfo.Fields.page).description("Page the Account"),
                                fieldWithPath(CommonPageInfo.Fields.size).description("Size the Account"))
                ));
    }


}
