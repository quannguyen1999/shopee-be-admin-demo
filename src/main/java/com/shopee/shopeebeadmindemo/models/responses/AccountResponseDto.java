package com.shopee.shopeebeadmindemo.models.responses;

import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountResponseDto extends CommonBaseModel {

    public UUID id;

    public String username;

}
