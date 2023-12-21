package com.shopee.shopeebeadmindemo.controllers.graphql;

import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccountGraphController {

    private final AccountService accountService;

    @QueryMapping
    public List<AccountResponseDto> listAccount(DataFetchingEnvironment environment) {
        return accountService.getAccounts(GraphQLUtils.getNameFieldGraphQL(environment));
    }
}
