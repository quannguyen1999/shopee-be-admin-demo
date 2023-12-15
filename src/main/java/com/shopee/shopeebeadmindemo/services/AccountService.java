package com.shopee.shopeebeadmindemo.services;

import com.shopee.shopeebeadmindemo.entities.Account;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;

import java.util.List;

public interface AccountService {
    void createAccount(Account account);

    List<AccountResponseDto> getAccounts();
}
