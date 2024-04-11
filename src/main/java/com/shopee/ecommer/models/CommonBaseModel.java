package com.shopee.ecommer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public List<String> listFields = new ArrayList<>();

    @JsonIgnore
    public List<Map<String, String>> listSorted = new ArrayList<>();

    @JsonIgnore
    public String listStringSorted;

}
