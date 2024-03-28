package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@NoArgsConstructor
public class OrderResponseDto extends CommonBaseModel {

    private String id;

    private String orderDate;

    private String shipCity;

    private String shippedDate;

    private String shipRegion;

    private String userName;

}
