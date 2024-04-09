package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@NoArgsConstructor
public class SupplierRequestDto extends CommonBaseModel {

    public String id;

    public String phone;

    public String address;

    public String companyName;

}
