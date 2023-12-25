package com.shopee.shopeebeadmindemo.controllers.graphql;

import com.shopee.shopeebeadmindemo.configs.LogNameMethod;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AccountGraphController {
    private final AccountService accountService;

    @LogNameMethod
    @QueryMapping
    public CommonPageInfo<AccountResponseDto> listAccount(@Argument AccountRequestDto accountRequestDto,
                                                          DataFetchingEnvironment environment) {
        return accountService.getAccounts(GraphQLUtils.getNameFieldGraphQL(environment), accountRequestDto);
    }
}
