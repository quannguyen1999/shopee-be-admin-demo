package com.shopee.shopeebeadmindemo.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountBatisService {

    List<Map<String, Object>> getListAccount();

}
