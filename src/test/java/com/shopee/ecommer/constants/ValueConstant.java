package com.shopee.ecommer.constants;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValueConstant {
    public static final String DATA_FAKE = "xx";

    public static final String PARAM_REFRESH_TOKEN = "refresh_token";

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final Date CURRENT_DATE = new Date();
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final String VALUE_USERNAME = "admin";

    public static final String VALUE_PHONE = "phone";
    public static final String VALUE_PASSWORD = "password";
}
