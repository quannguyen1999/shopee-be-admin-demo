package com.shopee.shopeebeadmindemo.models.requests;

import com.shopee.shopeebeadmindemo.entities.RoleAccount;
import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@FieldNameConstants
public class AccountRequestDto extends CommonBaseModel {

    public String username;

    private Date birthday;

    private Boolean gender;

    private String email;

    private String avatar;

    private Set<RoleAccount> roleAccountList = new HashSet<>();

}
