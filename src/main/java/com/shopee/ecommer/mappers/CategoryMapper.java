package com.shopee.ecommer.mappers;

import com.shopee.ecommer.entities.Category;
import com.shopee.ecommer.models.responses.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Map;

import static com.shopee.ecommer.models.CommonBaseModel.Fields.*;
import static com.shopee.ecommer.models.responses.CategoryResponseDto.Fields.*;


@Mapper
public interface CategoryMapper extends CommonUtilMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    //Note: Must Use Mappings to handler @FieldNameConstants
    @Mappings({
            @Mapping(source = id, target = id, qualifiedByName = CONVERT_TO_UUID),
            @Mapping(source = name, target = name, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = image, target = image, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = created, target = created, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_FORMAT),
            @Mapping(source = updated, target = updated, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_FORMAT),
            @Mapping(source = userCreated, target = userCreated, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = userUpdated, target = userUpdated, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = totalRecord, target = totalRecord, ignore = true),
            @Mapping(source = listFields, target = listFields, ignore = true),
            @Mapping(source = listSorted, target = listSorted, ignore = true),
            @Mapping(source = listStringSorted, target = listStringSorted, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = page, target = page, ignore = true),
            @Mapping(source = size, target = size, ignore = true),
    })
    CategoryResponseDto mapToCategoryResponseDto(Map<String, Object> data);

    CategoryResponseDto categoryToCategoryResponseDto(Category category);


}
