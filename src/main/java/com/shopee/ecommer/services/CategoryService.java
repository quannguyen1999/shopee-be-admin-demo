package com.shopee.ecommer.services;

import com.shopee.ecommer.models.requests.CategoryRequestDto;
import com.shopee.ecommer.models.responses.CategoryResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    void create(CategoryRequestDto categoryRequestDto);

    CommonPageInfo<CategoryResponseDto> getList(Map<String, String> listFieldRequest, CategoryRequestDto categoryRequestDto);

    List<HashMap<String, Object>> getListWithResultMap(CategoryRequestDto categoryRequestDto);

    List<String> getListField(CategoryRequestDto categoryRequestDto);

}
