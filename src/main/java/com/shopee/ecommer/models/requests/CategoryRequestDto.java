package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class CategoryRequestDto extends CommonBaseModel {

    private String id;

    private String name;

    private String image;

    public String createFromDate;

    public String createToDate;

}
