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
public class AccountRequestDto extends CommonBaseModel {

    public String id;

    public String username;

    public Date birthday;

    public Boolean gender;

    public String email;

    public String avatar;

    public Boolean isActive;
    //Search
    public String fromBirthday;

    public String toBirthday;

    public String createFromDate;

    public String createToDate;

    public Boolean mfaEnabled;

    private Set<RoleAccount> roleAccountList = new HashSet<>();

    private Boolean mfaRegistered;

}
