package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.entities.Account;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.repositories.AccountRepository;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.validators.AccountValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountValidator accountValidator;

    @Override
    public void createAccount(AccountRequestDto account) {
        accountValidator.validateCreateAccount(account);
        accountRepository.save(Account.builder()
                .username(account.getUsername())
                .password("password")
                .build());
    }

    @Override
    public List<AccountResponseDto> getAccounts() {
        return accountRepository
                .findAll()
                .stream().map(value ->
                        AccountResponseDto.builder()
                                .username(value.getUsername()).build())
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDto findByUserName(String userName) {
        Account account = accountRepository.findByUsername(userName);
        return ObjectUtils.isEmpty(account) ? null : AccountResponseDto.builder().username(userName).build();
    }
}
