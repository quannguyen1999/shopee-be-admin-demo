package com.shopee.shopeebeadmindemo.models.requests;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Data;

@Data
public class CategoryRequestDto extends CommonBaseModel {
    private String id;

    private String name;

    private String image;

}
