package com.shopee.ecommer.services;

import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AccountService {
    AccountResponseDto createAccount(AccountRequestDto account);

    AccountResponseDto updateAccount(AccountRequestDto account);

    CommonPageInfo<AccountResponseDto> getList(Map<String, String> listFields, AccountRequestDto requestDto);

    AccountResponseDto getInfo(String account);

    List<HashMap<String, Object>> getListWithResultMap(AccountRequestDto accountRequestDto);

    List<String> getListField(AccountRequestDto accountRequestDto);

    Object getToken(Oauth2ClientDto oauth2ClientDto);

    Object refreshToken(Oauth2ClientDto oauth2ClientDto);

}
