package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.AccountRequestDto;
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

    public void validateListFieldRequest(AccountRequestDto accountRequestDto) {
        checkList().accept(accountRequestDto.getListFields(), ACCOUNT_LIST_FIELD_INVALID);
    }

    private void validateCheckUserName(String username) {
        checkEmpty().accept(username, ACCOUNT_USERNAME_INVALID);
        checkIsExists().accept(accountRepository.findByUsername(username), ACCOUNT_USERNAME_IS_EXISTS);
    }
}
