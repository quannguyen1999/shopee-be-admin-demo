package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.ProductRequestDto;
import com.shopee.ecommer.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.shopee.ecommer.constants.MessageErrors.*;
import static com.shopee.ecommer.validators.CommonValidator.*;

@AllArgsConstructor
@Component
public class ProductValidator {

    private final CategoryRepository categoryRepository;

    public void validateCreateProduct(ProductRequestDto productRequestDto) {
        checkEmpty().accept(productRequestDto.getName(), PRODUCT_NAME_INVALID);
        checkEmpty().accept(productRequestDto.getName(), PRODUCT_NAME_INVALID);
        checkInteger().accept(productRequestDto.getQuantity(), PRODUCT_QUANTITY_INVALID);
        checkDouble().accept(productRequestDto.getPrice(), PRODUCT_PRICE_INVALID);
        checkDouble().accept(productRequestDto.getDiscount(), PRODUCT_DISCOUNT_INVALID);
        checkEmpty().accept(productRequestDto.getCategory(), PRODUCT_CATEGORY_INVALID);
        checkEmpty().accept(productRequestDto.getCategory().getId(), PRODUCT_CATEGORY_INVALID);
        checkEmpty().accept(categoryRepository.findById(UUID.fromString(productRequestDto.getCategory().getId())).orElse(null), PRODUCT_CATEGORY_NOT_EXISTS);
    }

    public void validateListFieldRequest(ProductRequestDto productRequestDto) {
        checkList().accept(productRequestDto.getListFields(), PRODUCT_LIST_FIELD_INVALID);
    }

}
