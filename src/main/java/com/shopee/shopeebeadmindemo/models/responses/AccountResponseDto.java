package com.shopee.shopeebeadmindemo.models.responses;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
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

}
