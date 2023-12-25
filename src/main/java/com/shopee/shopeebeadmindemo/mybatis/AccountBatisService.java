package com.shopee.shopeebeadmindemo.mybatis;

import com.shopee.shopeebeadmindemo.models.requests.AccountRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountBatisService {

    List<Map<String, Object>> getList(
            List<String> fields,
            AccountRequestDto request,
            Boolean isCountTotalPage);

}
