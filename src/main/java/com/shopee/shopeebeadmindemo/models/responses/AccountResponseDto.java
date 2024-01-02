package com.shopee.shopeebeadmindemo.models.responses;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@Builder
@FieldNameConstants
public class AccountResponseDto extends CommonBaseModel {

    public UUID id;

    public String username;

    private String birthday;

    private Boolean gender;

    private String email;

    private String avatar;

    private Boolean isActive;

}
