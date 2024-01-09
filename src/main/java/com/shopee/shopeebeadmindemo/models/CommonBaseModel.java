package com.shopee.shopeebeadmindemo.models;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@FieldNameConstants
public class CommonBaseModel {

    public Date created;

    public Date updated;

    public String userCreated;

    public String userUpdated;

    public Integer page;

    public Integer size;

    public Integer totalRecord;

    public List<String> listFields = new ArrayList<>();

    public List<Map<String, String>> listSorted = new ArrayList<>();

}
