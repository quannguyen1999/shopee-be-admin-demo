package com.shopee.ecommer.mybatis;

import com.shopee.ecommer.models.requests.SupplierRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SupplierBatisService {

    List<HashMap<String, Object>> getList(SupplierRequestDto request, Boolean isCountTotalPage);

}
