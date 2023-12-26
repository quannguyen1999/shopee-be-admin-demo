package com.shopee.shopeebeadmindemo.mybatis;

import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AccountBatisService {

    List<HashMap<String, Object>> getList(
            List<String> fields,
            AccountRequestDto request,
            Boolean isCountTotalPage);

}
