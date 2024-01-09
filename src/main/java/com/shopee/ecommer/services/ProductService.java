package com.shopee.ecommer.services;

import com.shopee.ecommer.models.requests.ProductRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.ProductResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);

    CommonPageInfo<ProductResponseDto> getList(Map<String, String> listFieldRequest, ProductRequestDto productRequestDto);

    List<HashMap<String, Object>> getListWithResultMap(ProductRequestDto productRequestDto);

    List<String> getListField(ProductRequestDto productRequestDto);
}
