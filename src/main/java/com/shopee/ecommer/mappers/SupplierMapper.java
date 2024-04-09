package com.shopee.ecommer.mappers;

import com.shopee.ecommer.entities.Supplier;
import com.shopee.ecommer.models.responses.SupplierResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Map;

import static com.shopee.ecommer.models.CommonBaseModel.Fields.*;
import static com.shopee.ecommer.models.responses.SupplierResponseDto.Fields.*;

@Mapper
public interface SupplierMapper extends CommonUtilMapper {
    SupplierMapper MAPPER = Mappers.getMapper(SupplierMapper.class);

    //Note: Must Use Mappings to handler @FieldNameConstants
    @Mappings({
            @Mapping(source = id, target = id, qualifiedByName = CONVERT_TO_UUID),
            @Mapping(source = phone, target = phone, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = address, target = address, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = companyName, target = companyName, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = listStringSorted, target = listStringSorted, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = totalRecord, target = totalRecord, ignore = true),
            @Mapping(source = listFields, target = listFields, ignore = true),
            @Mapping(source = listSorted, target = listSorted, ignore = true),
            @Mapping(source = page, target = page, ignore = true),
            @Mapping(source = size, target = size, ignore = true),
            @Mapping(source = created, target = created, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_FORMAT),
            @Mapping(source = updated, target = updated, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_FORMAT),
            @Mapping(source = userCreated, target = userCreated, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = userUpdated, target = userUpdated, qualifiedByName = CONVERT_TO_STRING),
    })
    SupplierResponseDto mapToSupplierResponseDto(Map<String, Object> data);

    SupplierResponseDto supplierToSupplierResponseDto(Supplier supplier);

}
