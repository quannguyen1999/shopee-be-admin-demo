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

    public String id;

    public String username;

    public Date birthday;

    public Boolean gender;

    public String email;

    public String avatar;

    public Boolean isActive;

    private Set<RoleAccount> roleAccountList = new HashSet<>();

    //Search
    public String fromBirthday;

    public String toBirthday;

    public String createFromDate;

    public String createToDate;

}
