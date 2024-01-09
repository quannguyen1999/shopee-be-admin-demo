package com.shopee.shopeebeadmindemo.mybatis;

import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CategoryBatisService {
    List<HashMap<String, Object>> getList(CategoryRequestDto request, Boolean isCountTotalPage);
}
