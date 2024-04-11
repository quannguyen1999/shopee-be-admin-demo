package com.shopee.ecommer.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
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
}
