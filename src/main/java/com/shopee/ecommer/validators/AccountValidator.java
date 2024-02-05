package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shopee.ecommer.constants.MessageErrors.*;

@AllArgsConstructor
@Component
public class AccountValidator extends CommonValidator {

    private final AccountRepository accountRepository;

    public void validateCreateAccount(AccountRequestDto accountRequestDto) {
        validateCheckUserName(accountRequestDto.getUsername());
    }

    public void validateGetToken(Oauth2ClientDto oauth2ClientDto) {
        checkEmpty().accept(oauth2ClientDto.getCode(), ACCOUNT_CODE_INVALID);
        checkEmpty().accept(oauth2ClientDto.getRedirectUrl(), ACCOUNT_REDIRECT_INVALID);
    }

    public void validateRefreshToken(Oauth2ClientDto oauth2ClientDto) {
        checkEmpty().accept(oauth2ClientDto.getRefreshToken(), ACCOUNT_REFRESH_TOKEN_INVALID);
    }

    public void validateListFieldRequest(AccountRequestDto accountRequestDto) {
        checkList().accept(accountRequestDto.getListFields(), ACCOUNT_LIST_FIELD_INVALID);
    }

    private void validateCheckUserName(String username) {
        checkEmpty().accept(username, ACCOUNT_USERNAME_INVALID);
        checkIsExists().accept(accountRepository.findByUsername(username), ACCOUNT_USERNAME_IS_EXISTS);
    }
}
