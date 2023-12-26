package com.shopee.shopeebeadmindemo.services;

import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;

import java.util.Map;

public interface AccountService {
    void createAccount(AccountRequestDto account);

    CommonPageInfo<AccountResponseDto> getAccounts(Map<String, String> listFields, AccountRequestDto requestDto);

}
