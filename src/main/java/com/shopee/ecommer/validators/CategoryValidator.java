package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.CategoryRequestDto;
import com.shopee.ecommer.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shopee.ecommer.constants.MessageErrors.CATEGORY_LIST_FIELD_INVALID;
import static com.shopee.ecommer.constants.MessageErrors.CATEGORY_NAME_INVALID;
import static com.shopee.ecommer.validators.CommonValidator.checkEmpty;
import static com.shopee.ecommer.validators.CommonValidator.checkList;

@AllArgsConstructor
@Component
public class CategoryValidator {

    private final CategoryRepository categoryRepository;

    public void validateCreateCategory(CategoryRequestDto categoryRequestDto) {

        checkEmpty().accept(categoryRequestDto.getName(), CATEGORY_NAME_INVALID);
    }

    public void validateListFieldRequest(CategoryRequestDto categoryRequestDto) {
        checkList().accept(categoryRequestDto.getListFields(), CATEGORY_LIST_FIELD_INVALID);
    }

}
