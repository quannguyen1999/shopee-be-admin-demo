package com.shopee.ecommer.mybatis;

import com.shopee.ecommer.models.requests.OrderRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface OrderBatisService {

    List<HashMap<String, Object>> getList(OrderRequestDto request, Boolean isCountTotalPage);

}
