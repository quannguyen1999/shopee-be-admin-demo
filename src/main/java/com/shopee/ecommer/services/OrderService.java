package com.shopee.ecommer.services;

import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.OrderResponseDto;
import com.shopee.ecommer.models.responses.SupplierResponseDto;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto data);

    OrderResponseDto updateOrder(OrderRequestDto data);

    CommonPageInfo<OrderResponseDto> getList(Map<String, String> listFields, OrderRequestDto data);

    CommonPageInfo<OrderResponseDto> getDetail(Map<String, String> listFields, OrderRequestDto data);

    List<HashMap<String, Object>> getListWithResultMap(OrderRequestDto orderRequestDto);

    List<String> getListField(OrderRequestDto data);
}
