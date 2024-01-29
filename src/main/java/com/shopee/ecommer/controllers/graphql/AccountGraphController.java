package com.shopee.ecommer.controllers.graphql;

import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.services.AccountService;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

@Controller
@AllArgsConstructor
public class AccountGraphController {
    private final AccountService accountService;

    private final ReportService reportService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @QueryMapping
    public CommonPageInfo<AccountResponseDto> listAccount(@Argument AccountRequestDto accountRequestDto, DataFetchingEnvironment environment) {
        return accountService.getList(GraphQLUtils.getNameFieldGraphQL(environment), accountRequestDto);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @QueryMapping
    public byte[] exportAccount(@Argument AccountRequestDto accountRequestDto) {
        List<HashMap<String, Object>> listResult = accountService.getListWithResultMap(accountRequestDto);
        return reportService.printReport(listResult, accountService.getListField(accountRequestDto));
    }
}
