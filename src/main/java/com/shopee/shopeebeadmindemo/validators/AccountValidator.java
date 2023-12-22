package com.shopee.shopeebeadmindemo.validators;

import com.shopee.shopeebeadmindemo.constants.MessageErrors;
import com.shopee.shopeebeadmindemo.exceptions.BadRequestException;
import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import com.shopee.shopeebeadmindemo.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@Component
public class AccountValidator {

    private final AccountRepository accountRepository;

    public void validateCreateAccount(AccountRequestDto accountRequestDto) {
        //Check UserName
        validateCheckUserName(accountRequestDto.getUsername());
    }

    private void validateCheckUserName(String username) {
        if (!StringUtils.hasLength(username)) {
            throw new BadRequestException(MessageErrors.USERNAME_INVALID);
        }
        if (!ObjectUtils.isEmpty(accountRepository.findByUsername(username))) {
            throw new BadRequestException(MessageErrors.USERNAME_IS_EXISTS);
        }
    }
}
