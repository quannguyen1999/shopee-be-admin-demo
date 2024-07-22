package com.shopee.ecommer.validators;

import com.shopee.ecommer.entities.Product;
import com.shopee.ecommer.models.requests.OrderDetailRequestDto;
import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.repositories.OrderRepository;
import com.shopee.ecommer.repositories.ProductRepository;
import com.shopee.ecommer.utils.FunctionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiConsumer;

import static com.shopee.ecommer.constants.MessageErrors.*;
import static com.shopee.ecommer.validators.CommonValidator.*;

@AllArgsConstructor
@Component
public class OrderValidator {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private static BiConsumer<Product, Integer> checkQuantity() {
        return (product, input) -> {
            if (product.getQuantity() <= 0) {
                badRequest().accept(PRODUCT_OUT_OF_STOCK);
            }
            if (input > product.getQuantity()) {
                badRequest().accept(PRODUCT_DO_NOT_HAVE_ENOUGH_STOCK);
            }
        };
    }

    public void validateCreateOrder(OrderRequestDto orderRequestDto) {
        checkEmpty().accept(orderRequestDto.getOrderDetailRequestDtoList(), ORDER_LIST_FIELD_INVALID);
        checkEmpty().accept(orderRequestDto.getShippedDate(), ORDER_SHIPPED_DATE_INVALID);
        FunctionUtils.parseStringToDate().apply(orderRequestDto.getShippedDate());
        for (OrderDetailRequestDto orderDetailRequestDto : orderRequestDto.getOrderDetailRequestDtoList()) {
            Product product = productRepository.findById(UUID.fromString(orderDetailRequestDto.getProductId())).orElse(null);
            checkEmpty().accept(orderDetailRequestDto.getProductId(), ORDER_PRODUCT_INVALID);
            checkUUID().accept(orderDetailRequestDto.getProductId(), ORDER_PRODUCT_ID_INVALID);
            checkEmpty().accept(product, ORDER_PRODUCT_ID_NOT_FOUND);
            checkQuantity().accept(product, orderDetailRequestDto.getQuantity());
        }
    }

    public void validateUpdateOrder(OrderRequestDto orderRequestDto) {

    }

    public void validateListFieldRequest(OrderRequestDto data) {
        checkList().accept(data.getListFields(), ORDER_LIST_FIELD_INVALID);
    }


}
