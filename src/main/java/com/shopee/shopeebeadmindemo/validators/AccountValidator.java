package com.shopee.shopeebeadmindemo.validators;

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
        
        if (!StringUtils.hasLength(accountRequestDto.getUsername())) {
            throw new BadRequestException("User Name is not valid");
        }
        if (!ObjectUtils.isEmpty(accountRepository.findByUsername(accountRequestDto.getUsername()))) {
            throw new BadRequestException("User Name is exists");
        }
    }
}
