package com.shopee.ecommer.services;

import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.SupplierResponseDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SupplierService {
    SupplierResponseDto createSupplier(SupplierRequestDto data);

    SupplierResponseDto updateSupplier(SupplierRequestDto data);

    CommonPageInfo<SupplierResponseDto> getList(Map<String, String> listFields, SupplierRequestDto data);

    List<HashMap<String, Object>> getListWithResultMap(SupplierRequestDto accountRequestDto);

    List<String> getListField(SupplierRequestDto data);

}
