package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shopee.ecommer.constants.MessageErrors.ACCOUNT_USERNAME_INVALID;
import static com.shopee.ecommer.constants.MessageErrors.ACCOUNT_USERNAME_IS_EXISTS;

@AllArgsConstructor
@Component
public class AccountValidator extends CommonValidator {

    private final AccountRepository accountRepository;

    public void validateCreateAccount(AccountRequestDto accountRequestDto) {
        //Check UserName
        validateCheckUserName(accountRequestDto.getUsername());
    }

    private void validateCheckUserName(String username) {
        checkEmpty().accept(username, ACCOUNT_USERNAME_INVALID);
        checkEmpty().accept(accountRepository.findByUsername(username), ACCOUNT_USERNAME_IS_EXISTS);
    }
}
