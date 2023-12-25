package com.shopee.shopeebeadmindemo.models;

import lombok.Data;

import java.util.Date;

@Data
public class CommonBaseModel {

    private Date created;

    private Date updated;

    private String userCreated;

    private String userUpdated;

    private Integer page;

    private Integer size;

}
