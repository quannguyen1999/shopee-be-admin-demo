package com.shopee.shopeebeadmindemo.controllers;

import com.shopee.shopeebeadmindemo.constants.PathApi;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = PathApi.ACCOUNT)
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<AccountResponseDto>> getListAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
    

}
