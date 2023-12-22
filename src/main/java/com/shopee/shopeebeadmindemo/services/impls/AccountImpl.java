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
import java.util.stream.Collectors;

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
        return new ArrayList<>(
                Arrays.asList(
                        AccountMapper.ID,
                        AccountMapper.EMAIL,
                        AccountMapper.GENDER,
                        AccountMapper.BIRTHDAY,
                        AccountMapper.USER_NAME,
                        AccountMapper.AVATAR)
        );
    }

    @Override
    public void createAccount(AccountRequestDto account) {

        accountValidator.validateCreateAccount(account);

        //TODO implement later
        accountRepository.save(Account.builder()
                .username(account.getUsername())
                .password("password")
                .build());

        log.info("send mail Async-Start");
        emailPublisher.publishEvent(EmailDto.builder().build());
        log.info("send mail Async-Continue Processing");
    }

    @Override
    public CommonPageInfo getAccounts(Map<String, String> listFields, Integer page, Integer size) {
//        log.info("send mail Async-Start");
//        accountPublisher.publishEvent("Test");
//        log.info("send mail Async-Continue Processing");
        List<String> listFieldParam = getList(listFields, getAllListAccountDefault());

        //Get Data
        List<AccountResponseDto> data = accountBatisService
                .getList(listFieldParam, page, size, false)
                .stream()
                .map(AccountMapper.MAPPER::mapToAccountResponseDto).toList();

        listFieldParam.add(CommonFieldValue.FIELD_TOTAL_PAGES);
        return CommonPageInfo.builder()
                .page(page)
                .size(size)
                .total(getCommonTotalPage(accountBatisService.getList(listFieldParam, page, size, true)))
                .data(data.stream().map(t -> (Object) t).collect(Collectors.toList()))
                .build();
    }

    @Override
    public AccountResponseDto findByUserName(String userName) {
        Account account = accountRepository.findByUsername(userName);
        return ObjectUtils.isEmpty(account) ? null : AccountResponseDto.builder().username(userName).build();
    }
}
