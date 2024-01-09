package com.shopee.shopeebeadmindemo.validators;

import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shopee.shopeebeadmindemo.constants.MessageErrors.CATEGORY_NAME_INVALID;

@AllArgsConstructor
@Component
public class CategoryValidator extends CommonValidator {

    private final CategoryRepository categoryRepository;

    public void validateCreateCategory(CategoryRequestDto categoryRequestDto) {
        checkEmpty().accept(categoryRequestDto.getName(), CATEGORY_NAME_INVALID);
    }

}
