package com.shopee.shopeebeadmindemo.controllers.rest;

import com.shopee.shopeebeadmindemo.constants.PathApi;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.services.ReportService;
import com.shopee.shopeebeadmindemo.services.impls.AccountImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = PathApi.ACCOUNT)
@AllArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    private final ReportService reportService;

    @RequestMapping(value = PathApi.LIST, method = RequestMethod.GET)
    public ResponseEntity<CommonPageInfo<AccountResponseDto>> getListAccounts(@RequestBody AccountRequestDto accountRequestDto) {
        return new ResponseEntity<>(accountService.getAccounts(null, accountRequestDto), HttpStatus.OK);
    }

    @RequestMapping(value = PathApi.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(AccountRequestDto accountRequestDto) {
        accountService.createAccount(accountRequestDto);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = PathApi.EXPORT, method = RequestMethod.POST)
    public ResponseEntity<byte[]> exportAccount(AccountRequestDto accountRequestDto) {
        List<HashMap<String, Object>> listResult = accountService.getListAccountsWithResultMap(null, accountRequestDto);
        return new ResponseEntity<>(reportService.printReport(listResult, AccountImpl.getAllListAccountDefault()), HttpStatus.OK);
    }


}
