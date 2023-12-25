package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.entities.Account;
import com.shopee.shopeebeadmindemo.events.publishers.AccountPublisher;
import com.shopee.shopeebeadmindemo.events.publishers.EmailPublisher;
import com.shopee.shopeebeadmindemo.mappers.AccountMapper;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.models.requests.EmailDto;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.mybatis.AccountBatisService;
import com.shopee.shopeebeadmindemo.repositories.AccountRepository;
import com.shopee.shopeebeadmindemo.services.AccountService;
import com.shopee.shopeebeadmindemo.validators.AccountValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto.Fields.*;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountImpl extends AdapterImpl implements AccountService {

    protected final AccountBatisService accountBatisService;
    protected final AccountRepository accountRepository;
    protected final AccountValidator accountValidator;
    protected final EmailPublisher emailPublisher;

    protected final AccountPublisher accountPublisher;

    private List<String> getAllListAccountDefault() {
        return new ArrayList<>(Arrays.asList(id, email, gender, birthday, username, avatar));
    }

    @Override
    public void createAccount(AccountRequestDto account) {
        accountValidator.validateCreateAccount(account);

        //TODO implement later
        accountRepository.save(Account.builder()
                .username(account.getUsername())
                .password(AccountRequestDto.Fields.avatar)
                .build());

        emailPublisher.publishEvent(EmailDto.builder().build());
    }

    @Override
    public CommonPageInfo<AccountResponseDto> getAccounts(Map<String, String> listFieldRequest, AccountRequestDto accountRequestDto) {
//        accountPublisher.publishEvent("Test");
        List<String> listFieldParam = getListField(listFieldRequest, getAllListAccountDefault());
        //Get Data
        List<AccountResponseDto> data = getListAccounts(listFieldParam, accountRequestDto);
        return CommonPageInfo.<AccountResponseDto>builder()
                .page(accountRequestDto.getPage())
                .size(accountRequestDto.getSize())
                .total(getCommonTotalPage().apply(accountBatisService.getList(listFieldParam, accountRequestDto, true)))
                .data(data)
                .build();
    }

    @Override
    public AccountResponseDto findByUserName(String userName) {
        Account account = accountRepository.findByUsername(userName);
        return ObjectUtils.isEmpty(account) ? null : AccountMapper.MAPPER.mapToAccountResponseDto(account);
    }

    private List<AccountResponseDto> getListAccounts(List<String> listFieldParam, AccountRequestDto accountRequestDto) {
        return accountBatisService
                .getList(listFieldParam, accountRequestDto, false)
                .stream()
                .map(AccountMapper.MAPPER::mapToAccountResponseDto).toList();
    }


}
