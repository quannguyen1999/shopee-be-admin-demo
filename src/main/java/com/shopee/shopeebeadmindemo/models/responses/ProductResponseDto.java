package com.shopee.shopeebeadmindemo.models.responses;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class ProductResponseDto extends CommonBaseModel {

    private String id;

    private String name;

    private String image;

    private Double quantity;

    private Double price;

    private Double discount;

}
