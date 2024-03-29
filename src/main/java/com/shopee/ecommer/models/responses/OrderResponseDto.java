package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.*;
import lombok.experimental.FieldNameConstants;


@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponseDto extends CommonBaseModel {

    private String id;

    private String orderDate;

    private String shipCity;

    private String shippedDate;

    private String shipRegion;

    private String userName;

}
