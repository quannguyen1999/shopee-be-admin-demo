package com.shopee.ecommer.services;

import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AccountService {
    void createAccount(AccountRequestDto account);

    CommonPageInfo<AccountResponseDto> getList(Map<String, String> listFields, AccountRequestDto requestDto);

    List<HashMap<String, Object>> getListWithResultMap(AccountRequestDto accountRequestDto);

    List<String> getListField(AccountRequestDto accountRequestDto);

}
