package com.shopee.ecommer.validators;

import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.entities.Product;
import com.shopee.ecommer.models.requests.OrderDetailRequestDto;
import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.repositories.OrderRepository;
import com.shopee.ecommer.repositories.ProductRepository;
import com.shopee.ecommer.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiConsumer;

import static com.shopee.ecommer.constants.MessageErrors.*;

@AllArgsConstructor
@Component
public class OrderValidator extends CommonValidator {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public void validateCreateOrder(OrderRequestDto orderRequestDto, Authentication authentication) {
        checkEmpty().accept(orderRequestDto.getOrderDetailRequestDtoList(), ORDER_LIST_FIELD_INVALID);
        for (OrderDetailRequestDto orderDetailRequestDto : orderRequestDto.getOrderDetailRequestDtoList()) {
            Product product = productRepository.findById(UUID.fromString(orderDetailRequestDto.getProductId())).get();
            checkEmpty().accept(orderDetailRequestDto.getProductId(), ORDER_PRODUCT_INVALID);
            checkUUID().accept(orderDetailRequestDto.getProductId(), ORDER_PRODUCT_ID_INVALID);
            checkEmpty().accept(product, ORDER_PRODUCT_ID_NOT_FOUND);

        }
    }

    private static BiConsumer<Product, MessageErrors> checkQuantity(){
        return (input, messageError) -> {
            try {
                if(input.getQuantity() <= 0){

                }
            } catch (IllegalArgumentException e) {
                badRequest().accept(messageError);
            }
        };
    }

    public void validateUpdateOrder(OrderRequestDto orderRequestDto) {

    }

    public void validateListFieldRequest(OrderRequestDto data) {
        checkList().accept(data.getListFields(), ORDER_LIST_FIELD_INVALID);
    }


}
