package com.shopee.shopeebeadmindemo.models.requests;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductRequestDto extends CommonBaseModel {

    private String name;

    private String image;

    private Double quantity;

    private Double price;

    private Double discount;

    private UUID idCategory;

}
