package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class CategoryResponseDto extends CommonBaseModel {

    public UUID id;

    public String name;

    public String image;

}
