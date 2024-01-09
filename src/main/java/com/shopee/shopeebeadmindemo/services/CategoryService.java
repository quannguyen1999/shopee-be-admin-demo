package com.shopee.shopeebeadmindemo.services;

import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    void create(CategoryRequestDto categoryRequestDto);

    CommonPageInfo<CategoryResponseDto> getList(Map<String, String> listFieldRequest, CategoryRequestDto categoryRequestDto);

    List<HashMap<String, Object>> getListWithResultMap(CategoryRequestDto categoryRequestDto);

    List<String> getListField(CategoryRequestDto categoryRequestDto);

}
