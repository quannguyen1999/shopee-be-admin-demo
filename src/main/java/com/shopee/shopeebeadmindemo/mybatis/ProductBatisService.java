package com.shopee.shopeebeadmindemo.mybatis;

import com.shopee.shopeebeadmindemo.models.requests.ProductRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductBatisService {
    List<HashMap<String, Object>> getList(ProductRequestDto request, Boolean isCountTotalPage);
}
