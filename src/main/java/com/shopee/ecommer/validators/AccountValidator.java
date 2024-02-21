package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.shopee.ecommer.constants.MessageErrors.*;

@AllArgsConstructor
@Component
public class AccountValidator extends CommonValidator {

    private static Date BIRTHDAY_LIMIT = new Date(2023 - 1900, Calendar.JANUARY, 1);

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private final AccountRepository accountRepository;

    public void validateCreateAccount(AccountRequestDto accountRequestDto) {
        validateCheckUserName(accountRequestDto.getUsername());
        validateBirthday(accountRequestDto.getBirthday());
        validateEmail(accountRequestDto.getEmail());
    }

    public void validateUpdateAccount(AccountRequestDto accountRequestDto) {
        checkEmpty().accept(accountRequestDto.getId(), ACCOUNT_ID_INVALID);
        checkIsNotExists().accept(accountRepository.findById(UUID.fromString(accountRequestDto.getId())).orElse(null), ACCOUNT_ID_NOT_EXISTS);
        checkIsNotExists().accept(accountRequestDto.getMfaEnabled(), ACCOUNT_MFA_ENABLED_INVALID);
        checkIsNotExists().accept(accountRequestDto.getMfaRegistered(), ACCOUNT_MFA_REGISTERED_INVALID);
        validateBirthday(accountRequestDto.getBirthday());
        validateEmail(accountRequestDto.getEmail());
    }

    public void validateGetToken(Oauth2ClientDto oauth2ClientDto) {
//        checkEmpty().accept(oauth2ClientDto.getCode(), ACCOUNT_CODE_INVALID);
//        checkEmpty().accept(oauth2ClientDto.getRedirectUrl(), ACCOUNT_REDIRECT_INVALID);
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
