package com.shopee.shopeebeadmindemo.mappers;

import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface AccountMapper extends CommonUtilMapper {
    String USER_NAME = "username";
    String ID = "id";
    String BIRTHDAY = "birthday";
    String GENDER = "gender";
    String EMAIL = "email";
    String AVATAR = "avatar";

    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = USER_NAME, target = USER_NAME, qualifiedByName = CONVERT_TO_STRING)
    @Mapping(source = ID, target = ID, qualifiedByName = CONVERT_TO_UUID)
    @Mapping(source = BIRTHDAY, target = BIRTHDAY, qualifiedByName = CONVERT_TO_STRING)
    @Mapping(source = GENDER, target = GENDER, qualifiedByName = CONVERT_TO_BOOLEAN)
    @Mapping(source = EMAIL, target = EMAIL, qualifiedByName = CONVERT_TO_STRING)
    @Mapping(source = AVATAR, target = AVATAR, qualifiedByName = CONVERT_TO_STRING)
    AccountResponseDto mapToAccountResponseDto(Map<String, Object> account);

}
