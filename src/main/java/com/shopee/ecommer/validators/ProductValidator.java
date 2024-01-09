package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.ProductRequestDto;
import com.shopee.ecommer.repositories.CategoryRepository;
import com.shopee.ecommer.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shopee.ecommer.constants.MessageErrors.*;

@AllArgsConstructor
@Component
public class ProductValidator extends CommonValidator {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public void validateCreateProduct(ProductRequestDto productRequestDto) {
        checkEmpty().accept(productRequestDto.getName(), PRODUCT_NAME_INVALID);
        checkEmpty().accept(productRequestDto.getName(), PRODUCT_NAME_INVALID);
        checkDouble().accept(productRequestDto.getQuantity(), PRODUCT_QUANTITY_INVALID);
        checkDouble().accept(productRequestDto.getPrice(), PRODUCT_PRICE_INVALID);
        checkDouble().accept(productRequestDto.getDiscount(), PRODUCT_DISCOUNT_INVALID);
        checkEmpty().accept(categoryRepository.findById(productRequestDto.getIdCategory()).orElse(null), PRODUCT_CATEGORY_NOT_EXISTS);
    }

}
