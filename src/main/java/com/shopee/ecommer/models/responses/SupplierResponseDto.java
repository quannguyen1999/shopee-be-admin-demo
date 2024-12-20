package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierResponseDto extends CommonBaseModel {

    public String id;

    public String phone;

    public String address;

    public String companyName;

    public static final class Fields {
        public static final String id = "id";
        public static final String phone = "phone";
        public static final String address = "address";
        public static final String companyName = "companyName";
    }
}
