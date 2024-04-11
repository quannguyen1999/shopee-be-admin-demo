package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.entities.RoleAccount;
import com.shopee.ecommer.models.CommonBaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@FieldNameConstants
@NoArgsConstructor
public class OtpRequestDto {

    private String username;

    private String value;

}
