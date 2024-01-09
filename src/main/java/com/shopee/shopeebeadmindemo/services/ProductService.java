package com.shopee.shopeebeadmindemo.services;

import com.shopee.shopeebeadmindemo.models.requests.ProductRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.models.responses.ProductResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);

    CommonPageInfo<ProductResponseDto> getList(Map<String, String> listFieldRequest, ProductRequestDto productRequestDto);

    List<HashMap<String, Object>> getListWithResultMap(ProductRequestDto productRequestDto);

    List<String> getListField(ProductRequestDto productRequestDto);
}
