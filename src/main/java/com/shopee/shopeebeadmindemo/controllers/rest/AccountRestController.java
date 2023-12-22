package com.shopee.shopeebeadmindemo.controllers.rest;

import com.shopee.shopeebeadmindemo.constants.PathApi;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = PathApi.ACCOUNT)
@AllArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @RequestMapping(value = PathApi.ACCOUNT_LIST, method = RequestMethod.GET)
    public ResponseEntity<CommonPageInfo> getListAccounts(@RequestParam("page") Integer page,
                                                          @RequestParam("size") Integer size) {
        return new ResponseEntity<>(accountService.getAccounts(null, page, size), HttpStatus.OK);
    }

    @RequestMapping(value = PathApi.ACCOUNT_CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(AccountRequestDto accountRequestDto) {
        accountService.createAccount(accountRequestDto);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
