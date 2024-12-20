package com.shopee.ecommer.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
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

    public String listStringSorted;

    public static final class Fields {
        public static final String created = "created";
        public static final String updated = "updated";
        public static final String userCreated = "userCreated";
        public static final String userUpdated = "userUpdated";
        public static final String page = "page";
        public static final String size = "size";
        public static final String totalRecord = "totalRecord";
        public static final String listFields = "listFields";
        public static final String listSorted = "listSorted";
        public static final String listStringSorted = "listStringSorted";
    }
}
