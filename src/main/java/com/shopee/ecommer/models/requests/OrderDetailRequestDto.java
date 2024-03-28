package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldNameConstants
@NoArgsConstructor
public class OrderDetailRequestDto extends CommonBaseModel {

    private String productId;

    private Double discount;

    private int quantity;

    private int totalAmount;

}
