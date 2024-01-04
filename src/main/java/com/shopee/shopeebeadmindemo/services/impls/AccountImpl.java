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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

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

    public static List<String> getAllListAccountDefault() {
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
        Integer totalRecord = getCommonTotalPage().apply(accountBatisService.getList(listFieldParam, accountRequestDto, true));
        return CommonPageInfo.<AccountResponseDto>builder()
                .page(accountRequestDto.getPage())
                .size(accountRequestDto.getSize())
                .total(totalRecord)
                .data(getListAccounts(listFieldParam, accountRequestDto, totalRecord))
                .build();
    }

    private List<AccountResponseDto> getListAccounts(List<String> listFieldParam, AccountRequestDto accountRequestDto, Integer totalRecord) {
        return (!ObjectUtils.isEmpty(accountRequestDto.getPage()) && !ObjectUtils.isEmpty(accountRequestDto.getSize()))
                && (accountRequestDto.getPage() * accountRequestDto.getSize()) < totalRecord ?
                accountBatisService
                        .getList(listFieldParam, accountRequestDto, false)
                        .stream()
                        .map(AccountMapper.MAPPER::mapToAccountResponseDto).toList() : new ArrayList<>();
    }

    @Override
    public List<HashMap<String, Object>> getListAccountsWithResultMap(AccountRequestDto accountRequestDto) {
        return accountBatisService.getList(
                CollectionUtils.isEmpty(accountRequestDto.getListFields()) ? getAllListAccountDefault() : accountRequestDto.getListFields(),
                accountRequestDto,
                false
        );
    }

    @Override
    public List<String> getListField(AccountRequestDto accountRequestDto) {
        return CollectionUtils.isEmpty(accountRequestDto.getListFields()) ? AccountImpl.getAllListAccountDefault() : accountRequestDto.getListFields();
    }


}
