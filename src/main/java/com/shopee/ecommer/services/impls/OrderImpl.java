package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.entities.Supplier;
import com.shopee.ecommer.mappers.SupplierMapper;
import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.OrderResponseDto;
import com.shopee.ecommer.models.responses.SupplierResponseDto;
import com.shopee.ecommer.mybatis.OrderBatisService;
import com.shopee.ecommer.mybatis.SupplierBatisService;
import com.shopee.ecommer.repositories.OrderRepository;
import com.shopee.ecommer.repositories.SupplierRepository;
import com.shopee.ecommer.services.OrderService;
import com.shopee.ecommer.services.SupplierService;
import com.shopee.ecommer.validators.OrderValidator;
import com.shopee.ecommer.validators.SupplierValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.shopee.ecommer.models.responses.SupplierResponseDto.Fields.*;
import static com.shopee.ecommer.utils.FunctionUtils.handlerListSort;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderImpl extends AdapterImpl implements OrderService {

    protected final OrderRepository orderRepository;

    protected final OrderValidator orderValidator;

    protected final OrderBatisService orderBatisService;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto data) {
        return null;
    }

    @Override
    public OrderResponseDto updateOrder(OrderRequestDto data) {
        return null;
    }

    @Override
    public CommonPageInfo<OrderResponseDto> getList(Map<String, String> listFields, OrderRequestDto data) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getListWithResultMap(OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public List<String> getListField(OrderRequestDto data) {
        return null;
    }
}
