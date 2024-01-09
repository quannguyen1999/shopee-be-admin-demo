package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;

@Data
public class CategoryRequestDto extends CommonBaseModel {
    private String id;

    private String name;

    private String image;

}
