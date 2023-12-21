package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.entities.Account;
import com.shopee.shopeebeadmindemo.mappers.AccountMapper;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.mybatis.AccountBatisService;
import com.shopee.shopeebeadmindemo.repositories.AccountRepository;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.validators.AccountValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountImpl implements AccountService {

    protected final AccountBatisService accountBatisService;
    protected final AccountRepository accountRepository;
    protected final AccountValidator accountValidator;

    @Override
    public void createAccount(AccountRequestDto account) {
        accountValidator.validateCreateAccount(account);
        //TODO implement later
        accountRepository.save(Account.builder()
                .username(account.getUsername())
                .password("password")
                .build());
    }

    @Override
    public List<AccountResponseDto> getAccounts(List<String> listFields) {
        return accountBatisService
                .getListAccount(CollectionUtils.isEmpty(listFields) ? getAllListAccountDefault() : listFields)
                .stream()
                .map(AccountMapper.MAPPER::mapToAccountResponseDto)
                .collect(Collectors.toList());
    }

    private List<String> getAllListAccountDefault() {
        return Arrays.asList(
                AccountMapper.ID,
                AccountMapper.EMAIL,
                AccountMapper.GENDER,
                AccountMapper.BIRTHDAY,
                AccountMapper.USER_NAME,
                AccountMapper.AVATAR);
    }

    @Override
    public AccountResponseDto findByUserName(String userName) {
        Account account = accountRepository.findByUsername(userName);
        return ObjectUtils.isEmpty(account) ? null : AccountResponseDto.builder().username(userName).build();
    }
}
