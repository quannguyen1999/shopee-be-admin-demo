package com.shopee.shopeebeadmindemo.models;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

@Data
@FieldNameConstants
public class CommonBaseModel {

    public Date created;

    public Date updated;

    public String userCreated;

    public String userUpdated;

    public Integer page;

    public Integer size;

}
