package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductRequestDto extends CommonBaseModel {

    private String id;

    private String name;

    private String image;

    private Double quantity;

    private Double price;

    private Double discount;

    private UUID idCategory;

    public String createFromDate;

    public String createToDate;

}
