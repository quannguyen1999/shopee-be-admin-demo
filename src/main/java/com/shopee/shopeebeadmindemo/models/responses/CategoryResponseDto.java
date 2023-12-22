package com.shopee.shopeebeadmindemo.models.responses;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDto extends CommonBaseModel {

    private String name;

    private String image;

}
