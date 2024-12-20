package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto extends CommonBaseModel {

    public UUID id;

    public String name;

    public String image;

    public static final class Fields {
        public static final String id = "id";
        public static final String name = "name";
        public static final String image = "image";
    }
}
