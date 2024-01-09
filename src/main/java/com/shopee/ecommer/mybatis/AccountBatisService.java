package com.shopee.ecommer.mybatis;

import com.shopee.ecommer.models.requests.AccountRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AccountBatisService {

    List<HashMap<String, Object>> getList(AccountRequestDto request, Boolean isCountTotalPage);

}
