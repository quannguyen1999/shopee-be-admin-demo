package com.shopee.ecommer.validators;

import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.shopee.ecommer.constants.MessageErrors.*;
import static com.shopee.ecommer.validators.CommonValidator.*;

@AllArgsConstructor
@Component
public class SupplierValidator {

    private final SupplierRepository supplierRepository;

    public void validateCreateSupplier(SupplierRequestDto supplierRequestDto) {
        checkEmpty().accept(supplierRequestDto.getPhone(), SUPPLIER_PHONE_INVALID);
        checkEmpty().accept(supplierRequestDto.getAddress(), SUPPLIER_ADDRESS_INVALID);
    }

    public void validateUpdateSupplier(SupplierRequestDto supplierRequestDto) {
        checkEmpty().accept(supplierRequestDto.getId(), SUPPLIER_ID_INVALID);
        checkIsExists().accept(supplierRepository.findById(UUID.fromString(supplierRequestDto.getId())), SUPPLIER_ID_NOT_EXISTS);
    }

    public void validateListFieldRequest(SupplierRequestDto data) {
        checkList().accept(data.getListFields(), SUPPLIER_LIST_FIELD_INVALID);
    }


}
