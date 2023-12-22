package com.shopee.shopeebeadmindemo.models.requests;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Data;

@Data
public class CategoryRequestDto extends CommonBaseModel {
    
    private String name;

    private String image;

}
