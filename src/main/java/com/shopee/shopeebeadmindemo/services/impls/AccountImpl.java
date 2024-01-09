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

    public static List<String> getAllListDefault() {
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
    public CommonPageInfo<AccountResponseDto> getList(Map<String, String> listFieldRequest,
                                                      AccountRequestDto accountRequestDto) {
//        accountPublisher.publishEvent("Test");
        accountRequestDto.setListFields(convertListFieldRequest(listFieldRequest, getAllListDefault()));
        accountRequestDto.setTotalRecord(getCommonTotalPage().apply(accountBatisService.getList(accountRequestDto, true)));
        return CommonPageInfo.<AccountResponseDto>builder()
                .page(accountRequestDto.getPage())
                .size(accountRequestDto.getSize())
                .total(accountRequestDto.getTotalRecord())
                .data(handlerList(accountRequestDto))
                .build();
    }

    private List<AccountResponseDto> handlerList(AccountRequestDto accountRequestDto) {
        return checkPageSize()
                .test(CommonPageInfo.builder()
                        .page(accountRequestDto.getPage())
                        .size(accountRequestDto.getSize())
                        .total(accountRequestDto.getTotalRecord())
                        .build()) ? accountBatisService.getList(accountRequestDto, false)
                .stream().map(AccountMapper.MAPPER::mapToAccountResponseDto).toList() : new ArrayList<>();
    }

    @Override
    public List<HashMap<String, Object>> getListWithResultMap(AccountRequestDto accountRequestDto) {
        return accountBatisService.getList(accountRequestDto, false);
    }

    @Override
    public List<String> getListField(AccountRequestDto accountRequestDto) {
        return checkList().apply(accountRequestDto.getListFields(), AccountImpl.getAllListDefault());
    }


}
