package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.entities.Account;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountImpl implements AccountService {

    @Override
    public void createAccount(Account account) {

    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        return null;
    }
}
