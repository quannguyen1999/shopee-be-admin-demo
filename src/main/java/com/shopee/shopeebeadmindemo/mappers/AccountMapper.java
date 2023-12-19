package com.shopee.shopeebeadmindemo.mappers;

import com.shopee.shopeebeadmindemo.entities.Account;
import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    Account accountResponseDtoToAccount(AccountResponseDto accountResponseDto);

    AccountResponseDto accountToAccountResponseDto(Account account);
}
