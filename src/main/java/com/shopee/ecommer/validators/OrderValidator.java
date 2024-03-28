package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.repositories.OrderRepository;
import com.shopee.ecommer.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.shopee.ecommer.constants.MessageErrors.*;

@AllArgsConstructor
@Component
public class OrderValidator extends CommonValidator {

    private final OrderRepository orderRepository;

    public void validateCreateOrder(OrderRequestDto orderRequestDto) {

    }

    public void validateUpdateOrder(OrderRequestDto orderRequestDto) {

    }

    public void validateListFieldRequest(OrderRequestDto data) {
        checkList().accept(data.getListFields(), ORDER_LIST_FIELD_INVALID);
    }


}
