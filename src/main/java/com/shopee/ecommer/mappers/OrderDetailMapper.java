package com.shopee.ecommer.mappers;

import com.shopee.ecommer.entities.Account;
import com.shopee.ecommer.entities.OrderDetail;
import com.shopee.ecommer.entities.OrderEcommer;
import com.shopee.ecommer.models.responses.OrderDetailResponseDto;
import com.shopee.ecommer.models.responses.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.ObjectUtils;

import java.util.Map;

import static com.shopee.ecommer.models.CommonBaseModel.Fields.*;
import static com.shopee.ecommer.models.responses.OrderResponseDto.Fields.*;

@Mapper
public interface OrderDetailMapper extends CommonUtilMapper {

    String CONVERT_TO_USERNAME = "convertToUsername";

    @Named(CONVERT_TO_USERNAME)
    default String convertToUserName(Object fieldValue) {
        return !ObjectUtils.isEmpty(fieldValue) ? ((Account)fieldValue).getUsername() : null;
    }

    OrderDetailMapper MAPPER = Mappers.getMapper(OrderDetailMapper.class);

    OrderResponseDto orderEcommerToOrderResponseDto(OrderEcommer orderEcommer);

    OrderDetailResponseDto orderDetailToOrderDetailResponseDto(OrderDetail orderDetail);
    @Mappings({
            @Mapping(source = id, target = id, qualifiedByName = CONVERT_TO_UUID),
            @Mapping(source = orderDate, target = orderDate, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = shipCity, target = shipCity, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = shippedDate, target = shippedDate, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = shipRegion, target = shipRegion, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = username, target = username, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = orderDetailList, target = orderDetailList, ignore = true),
            @Mapping(source = created, target = created, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_TIME_FORMAT),
            @Mapping(source = updated, target = updated, qualifiedByName = CONVERT_TO_STRING, dateFormat = DATE_TIME_FORMAT),
            @Mapping(source = userCreated, target = userCreated, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = userUpdated, target = userUpdated, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = totalRecord, target = totalRecord, ignore = true),
            @Mapping(source = listFields, target = listFields, ignore = true),
            @Mapping(source = listSorted, target = listSorted, ignore = true),
            @Mapping(source = listStringSorted, target = listStringSorted, qualifiedByName = CONVERT_TO_STRING),
            @Mapping(source = page, target = page, ignore = true),
            @Mapping(source = size, target = size, ignore = true),
    })
    OrderResponseDto mapToOrderResponseDto(Map<String, Object> data);



}
