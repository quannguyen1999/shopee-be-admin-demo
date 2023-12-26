package com.shopee.shopeebeadmindemo.controllers.graphql;

import com.shopee.shopeebeadmindemo.configs.LogNameMethod;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.services.ReportService;
import com.shopee.shopeebeadmindemo.services.impls.AccountImpl;
import com.shopee.shopeebeadmindemo.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

@Controller
@AllArgsConstructor
public class AccountGraphController {
    private final AccountService accountService;

    private final ReportService reportService;

    @LogNameMethod
    @QueryMapping
    public CommonPageInfo<AccountResponseDto> listAccount(@Argument AccountRequestDto accountRequestDto,
                                                          DataFetchingEnvironment environment) {
        return accountService.getAccounts(GraphQLUtils.getNameFieldGraphQL(environment), accountRequestDto);
    }

    @LogNameMethod
    @QueryMapping
    public byte[] exportAccount(@Argument AccountRequestDto accountRequestDto,
                                DataFetchingEnvironment environment) {
        List<HashMap<String, Object>> listResult = accountService.getListAccountsWithResultMap(GraphQLUtils.getNameFieldGraphQL(environment), accountRequestDto);
        return reportService.printReport(listResult, AccountImpl.getAllListAccountDefault());
    }
}
