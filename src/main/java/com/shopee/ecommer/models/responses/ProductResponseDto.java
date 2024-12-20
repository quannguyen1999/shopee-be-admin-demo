package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto extends CommonBaseModel {

    public UUID id;

    public String name;

    public String image;

    public Double quantity;

    public Double price;

    public Double discount;

    public String description;

    public static final class Fields {
        public static final String id = "id";
        public static final String name = "name";
        public static final String image = "image";
        public static final String quantity = "quantity";
        public static final String price = "price";
        public static final String discount = "discount";
        public static final String description = "description";
    }
}
