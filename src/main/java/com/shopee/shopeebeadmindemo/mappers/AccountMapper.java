package com.shopee.shopeebeadmindemo.mappers;

import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "username", target = "username", qualifiedByName = "convertToString")
    @Mapping(source = "id", target = "id", qualifiedByName = "convertToUUID")
    @Mapping(source = "password", target = "password", qualifiedByName = "convertToString")
    @Mapping(source = "birthday", target = "birthday", qualifiedByName = "convertToString")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "convertToDate")
    @Mapping(source = "email", target = "email", qualifiedByName = "convertToString")
    @Mapping(source = "avatar", target = "avatar", qualifiedByName = "convertToString")
    AccountResponseDto mapToAccountResponseDto(Map<String, Object> account);

    @Named("convertToString")
    default String convertToString(Object fieldValue) {
        return fieldValue != null ? fieldValue.toString() : null;
    }

    @Named("convertToUUID")
    default UUID convertToUUID(Object fieldValue) {
        return fieldValue != null ? UUID.fromString(fieldValue.toString()) : null;
    }

    @Named("convertToBoolean")
    default Boolean convertToBoolean(Object fieldValue) {
        return fieldValue != null ? Boolean.getBoolean(fieldValue.toString()) : null;
    }

    @Named("convertToDate")
    default Date convertToDate(Object fieldValue) {
        return fieldValue != null ? Date.valueOf(fieldValue.toString()) : null;
    }


}
