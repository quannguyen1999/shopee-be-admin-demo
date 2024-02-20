package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class AccountResponseDto extends CommonBaseModel {

    public UUID id;

    public String username;

    public String birthday;

    public Boolean gender;

    public String email;

    public String avatar;

    public Boolean isActive;

    public Boolean mfaEnabled;

    public Boolean mfaRegistered;

}
