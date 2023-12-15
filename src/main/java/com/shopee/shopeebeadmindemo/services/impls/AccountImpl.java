package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.entities.Account;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.repositories.AccountRepository;
import com.shopee.shopeebeadmindemo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createAccount(AccountRequestDto account) {
        accountRepository.save(Account.builder()
                .username(account.getUsername())
                .password("password")
                .build());
    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        return accountRepository
                .findAll()
                .stream().map( value ->
                        AccountResponseDto.builder()
                                .username(value.getUsername()).build())
                .collect(Collectors.toList());
    }
}
