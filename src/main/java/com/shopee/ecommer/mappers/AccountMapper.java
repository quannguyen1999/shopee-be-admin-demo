package com.shopee.ecommer.mappers;

import com.shopee.ecommer.entities.Account;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Map;

import static com.shopee.ecommer.models.CommonBaseModel.Fields.*;
import static com.shopee.ecommer.models.responses.AccountResponseDto.Fields.*;

@Mapper
public interface AccountMapper extends CommonUtilMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    //Note: Must Use Mappings to handler @FieldNameConstants
    @Mappings({
            @Mapping(source = username, target = username, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = id, target = id, qualifiedByName = CONVERT_TO_UUID),
            @Mapping(source = birthday, target = birthday, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = gender, target = gender, qualifiedByName = CONVERT_TO_BOOLEAN),
            @Mapping(source = email, target = email, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = avatar, target = avatar, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = isActive, target = isActive, qualifiedByName = CONVERT_TO_BOOLEAN),
            @Mapping(source = created, target = created, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_FORMAT),
            @Mapping(source = updated, target = updated, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_FORMAT),
            @Mapping(source = userCreated, target = userCreated, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = userUpdated, target = userUpdated, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = totalRecord, target = totalRecord, ignore = true),
            @Mapping(source = listFields, target = listFields, ignore = true),
            @Mapping(source = listSorted, target = listSorted, ignore = true),
            @Mapping(source = mfaEnabled, target = mfaEnabled, qualifiedByName = CONVERT_TO_BOOLEAN),
            @Mapping(source = mfaRegistered, target = mfaRegistered, qualifiedByName = CONVERT_TO_BOOLEAN),
            @Mapping(source = listStringSorted, target = listStringSorted, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = page, target = page, ignore = true),
            @Mapping(source = size, target = size, ignore = true),
    })
    AccountResponseDto mapToAccountResponseDto(Map<String, Object> data);

    Account mapToAccount(AccountRequestDto accountRequestDto);

    AccountResponseDto accountToAccountResponseDto(Account account);

}
