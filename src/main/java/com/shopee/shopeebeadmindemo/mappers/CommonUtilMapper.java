package com.shopee.shopeebeadmindemo.mappers;

import org.mapstruct.Named;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

public interface CommonUtilMapper {
    //Name Convert Types
    String CONVERT_TO_STRING = "convertToString";
    String CONVERT_TO_UUID = "convertToUUID";
    String CONVERT_TO_BOOLEAN = "convertToBoolean";

    //Format Date
    String DATE_FORMAT = "yyyy-MM-dd";

    @Named(CONVERT_TO_STRING)
    default String convertToString(Object fieldValue) {
        return !ObjectUtils.isEmpty(fieldValue) ? fieldValue.toString() : null;
    }

    @Named(CONVERT_TO_UUID)
    default UUID convertToUUID(Object fieldValue) {
        return !ObjectUtils.isEmpty(fieldValue) ? UUID.fromString(fieldValue.toString()) : null;
    }

    @Named(CONVERT_TO_BOOLEAN)
    default Boolean convertToBoolean(Object fieldValue) {
        return !ObjectUtils.isEmpty(fieldValue) ? Boolean.getBoolean(fieldValue.toString()) : null;
    }
}
