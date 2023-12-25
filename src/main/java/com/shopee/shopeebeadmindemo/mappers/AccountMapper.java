package com.shopee.shopeebeadmindemo.mappers;

import com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Map;

import static com.shopee.shopeebeadmindemo.models.responses.AccountResponseDto.Fields.*;

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
            @Mapping(source = avatar, target = avatar, qualifiedByName = CONVERT_TO_STRING)
    })
    AccountResponseDto mapToAccountResponseDto(Map<String, Object> account);

}
