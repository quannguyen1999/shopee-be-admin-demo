package com.shopee.ecommer.validators;

import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.entities.Account;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.requests.OtpRequestDto;
import com.shopee.ecommer.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.shopee.ecommer.constants.MessageErrors.*;

@AllArgsConstructor
@Component
public class AccountValidator extends CommonValidator {

    private final AccountRepository accountRepository;

    public void validateCreateAccount(AccountRequestDto accountRequestDto) {
        validateCheckUserName(accountRequestDto.getUsername());
        validateBirthday(accountRequestDto.getBirthday());
        validateEmail(accountRequestDto.getEmail());
    }

    public void validateRegisterAccount(AccountRequestDto accountRequestDto) {
        checkEmpty().accept(accountRequestDto.getUsername(), ACCOUNT_USERNAME_INVALID);
//        checkCondition().accept(!accountRequestDto.getUsername().matches(PHONE_REGEX), ACCOUNT_PHONE_INVALID);
        checkCondition().accept(accountRequestDto.getUsername().length() < 9, ACCOUNT_PHONE_INVALID);

        Account account = accountRepository.findByUsername(accountRequestDto.getUsername());
        checkCondition().accept(!ObjectUtils.isEmpty(account) && account.getIsActive(), ACCOUNT_IS_ACTIVE);
    }

    public void validateVerifyAccount(OtpRequestDto otpRequestDto) {
        checkEmpty().accept(otpRequestDto.getUsername(), ACCOUNT_USERNAME_INVALID);
        checkEmpty().accept(otpRequestDto.getValue(), ACCOUNT_OTP_INVALID);

        Account account = accountRepository.findByUsername(otpRequestDto.getUsername());
        checkCondition().accept(ObjectUtils.isEmpty(account), ACCOUNT_USERNAME_NOT_EXISTS);
        checkCondition().accept(account.getIsActive(), ACCOUNT_USERNAME_IS_ACTIVE);
    }


    public void validateUpdateAccount(AccountRequestDto accountRequestDto) {
        validateId(accountRequestDto.getId());
        checkIsNotExists().accept(accountRequestDto.getMfaEnabled(), ACCOUNT_MFA_ENABLED_INVALID);
        checkIsNotExists().accept(accountRequestDto.getMfaRegistered(), ACCOUNT_MFA_REGISTERED_INVALID);
        validateBirthday(accountRequestDto.getBirthday());
        validateEmail(accountRequestDto.getEmail());
    }

    void validateId(String id) {
        checkEmpty().accept(id, ACCOUNT_ID_INVALID);

        Account account = accountRepository.findById(UUID.fromString(id)).orElse(null);
        checkIsNotExists().accept(account, ACCOUNT_ID_NOT_EXISTS);
        assert account != null;
        if (account.getUsername().equalsIgnoreCase("admin")) {
            badRequest().accept(ACCOUNT_ADMIN_CAN_NOT_EDIT);
        }
    }

    public void validateGetToken(Oauth2ClientDto oauth2ClientDto) {

    }

    public void validateRefreshToken(Oauth2ClientDto oauth2ClientDto) {
        checkEmpty().accept(oauth2ClientDto.getRefreshToken(), ACCOUNT_REFRESH_TOKEN_INVALID_OR_EXPIRED);
    }

    public void validateListFieldRequest(AccountRequestDto accountRequestDto) {
        checkList().accept(accountRequestDto.getListFields(), ACCOUNT_LIST_FIELD_INVALID);
    }

    private void validateCheckUserName(String username) {
        checkEmpty().accept(username, ACCOUNT_USERNAME_INVALID);
        checkIsExists().accept(accountRepository.findByUsername(username), ACCOUNT_USERNAME_IS_EXISTS);

    }

    private void validateBirthday(Date value) {
        checkEmpty().accept(value, ACCOUNT_BIRTHDAY_INVALID);
        checkCondition().accept(!value.before(BIRTHDAY_LIMIT), ACCOUNT_BIRTHDAY_INVALID);
    }

    private void validateEmail(String email) {
        checkEmpty().accept(email, ACCOUNT_EMAIL_INVALID);
        checkCondition().accept(!email.matches(EMAIL_REGEX), ACCOUNT_EMAIL_INVALID);

    }
}
