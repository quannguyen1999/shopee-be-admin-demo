package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.constants.CommonFieldValue;
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

        log.info("send mail Async-Start");
        emailPublisher.publishEvent(EmailDto.builder().build());
        log.info("send mail Async-Continue Processing");
    }

    @Override
    public CommonPageInfo getAccounts(Map<String, String> listFieldsExists, Integer page, Integer size) {
//        log.info("send mail Async-Start");
//        accountPublisher.publishEvent("Test");
//        log.info("send mail Async-Continue Processing");
        List<String> listFieldParam = getListField(listFieldsExists, getAllListAccountDefault());

        //Get Data
        List<AccountResponseDto> data = accountBatisService
                .getList(listFieldParam, page, size, false)
                .stream()
                .map(AccountMapper.MAPPER::mapToAccountResponseDto).toList();

        listFieldParam.add(CommonFieldValue.FIELD_TOTAL_PAGES);
        return CommonPageInfo.builder()
                .page(page)
                .size(size)
                .total(getCommonTotalPage().apply(accountBatisService.getList(listFieldParam, page, size, true)))
                .data(convertToObject(data))
                .build();
    }

    @Override
    public AccountResponseDto findByUserName(String userName) {
        Account account = accountRepository.findByUsername(userName);
        return ObjectUtils.isEmpty(account) ? null : AccountResponseDto.builder().username("admin").build();
    }
}
