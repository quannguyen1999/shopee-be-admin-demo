package com.shopee.shopeebeadmindemo.models.requests;

import com.shopee.shopeebeadmindemo.entities.RoleAccount;
import com.shopee.shopeebeadmindemo.models.CommonBaseModel;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.*;

@Data
@FieldNameConstants
public class AccountRequestDto extends CommonBaseModel {

    public String username;

    private Date birthday;

    private Boolean gender;

    private String email;

    private String avatar;

    private Set<RoleAccount> roleAccountList = new HashSet<>();

    //Search
    public List<String> listFields = new ArrayList<>();

    public Date fromBirthday;

    public Date toBirthday;

}
