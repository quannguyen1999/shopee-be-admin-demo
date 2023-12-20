package com.shopee.shopeebeadmindemo.services.mybatis;

import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountBatisService {

    List<AccountResponseDto> getListAccount();

}
