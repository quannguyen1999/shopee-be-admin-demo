package com.shopee.shopeebeadmindemo.services;

import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;

import java.util.List;

public interface AccountService {
    void createAccount(AccountRequestDto account);

    List<AccountResponseDto> getAccounts();

    AccountResponseDto findByUserName(String userName);
}
