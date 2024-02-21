package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.entities.Account;
import com.shopee.ecommer.events.publishers.AccountPublisher;
import com.shopee.ecommer.events.publishers.EmailPublisher;
import com.shopee.ecommer.exceptions.UnauthorizedRequestException;
import com.shopee.ecommer.feignClient.AccountServerClient;
import com.shopee.ecommer.mappers.AccountMapper;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.EmailDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.mybatis.AccountBatisService;
import com.shopee.ecommer.repositories.AccountRepository;
import com.shopee.ecommer.services.AccountService;
import com.shopee.ecommer.validators.AccountValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.shopee.ecommer.models.responses.AccountResponseDto.Fields.*;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountImpl extends AdapterImpl implements AccountService {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final static Random rand = new Random();
    protected final AccountBatisService accountBatisService;
    protected final AccountRepository accountRepository;
    protected final AccountValidator accountValidator;
    protected final EmailPublisher emailPublisher;
    protected final AccountPublisher accountPublisher;
    protected final AccountServerClient accountServerClient;
    protected final PasswordEncoder passwordEncoder;

    @Value("${custom.security.clientId}")
    private String clientId;
    @Value("${custom.security.clientSecret}")
    private String clientSecret;

    public static List<String> getAllListDefault() {
        return new ArrayList<>(Arrays.asList(id, email, gender, birthday, username, avatar));
    }

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
        //Validator
        accountValidator.validateCreateAccount(accountRequestDto);

        //Save
        String password = String.valueOf(rand.nextInt(100000 + rand.nextInt(900000)));
//        if (logger.isTraceEnabled()) {
        log.info(String.valueOf(logger.isTraceEnabled()));
        log.info("Password:" + password);
//        }

        Account accountConvert = AccountMapper.MAPPER.mapToAccount(accountRequestDto);
        accountConvert.setPassword(passwordEncoder.encode(password));
        accountConvert.setMfaEnabled(true);
        accountConvert.setMfaRegistered(false);

        Account accountSave = accountRepository.save(accountConvert);

        //Sent Mail
        emailPublisher.publishEvent(EmailDto.builder().build());
        //Convert To Response
        return AccountMapper.MAPPER.accountToAccountResponseDto(accountSave);
    }

    @Override
    public AccountResponseDto updateAccount(AccountRequestDto accountRequestDto) {
        //Validator
        accountValidator.validateUpdateAccount(accountRequestDto);

        //Update
        Account account = accountRepository.findById(UUID.fromString(accountRequestDto.getId())).get();
        account.setMfaEnabled(accountRequestDto.getMfaEnabled());
        account.setMfaRegistered(accountRequestDto.getMfaRegistered());

        return AccountMapper.MAPPER.accountToAccountResponseDto(accountRepository.save(account));
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
        accountValidator.validateListFieldRequest(accountRequestDto);
        return accountBatisService.getList(accountRequestDto, false);
    }

    @Override
    public List<String> getListField(AccountRequestDto accountRequestDto) {
        return checkList().apply(accountRequestDto.getListFields(), AccountImpl.getAllListDefault());
    }

    @Override
    public Object getToken(Oauth2ClientDto oauth2ClientDto) {
        //Validator
        accountValidator.validateGetToken(oauth2ClientDto);

        //request get token
        return accountServerClient.getToken(
                clientId,
                clientSecret,
                oauth2ClientDto.getCode(),
                AuthorizationGrantType.AUTHORIZATION_CODE.getValue(),
                oauth2ClientDto.getRedirectUrl()
        );
    }

    @Override
    public Object refreshToken(Oauth2ClientDto oauth2ClientDto) {
        //Validator
        accountValidator.validateRefreshToken(oauth2ClientDto);

        //request get token
        Object result;
        try {
            result = accountServerClient.getRefreshToken(
                    clientId,
                    clientSecret,
                    oauth2ClientDto.getRefreshToken(),
                    AuthorizationGrantType.REFRESH_TOKEN.getValue()
            );
        } catch (Exception e) {
            throw new UnauthorizedRequestException(MessageErrors.ACCOUNT_REFRESH_TOKEN_INVALID_OR_EXPIRED);
        }

        return result;
    }


}
