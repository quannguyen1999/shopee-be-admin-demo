package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto extends CommonBaseModel {

    public String id;

    public String orderDate;

    public String shipCity;

    public String shippedDate;

    public String shipRegion;

    public String username;

    public List<OrderDetailResponseDto> orderDetailList;


    public static final class Fields {
        public static final String id = "id";
        public static final String orderDate = "orderDate";
        public static final String shipCity = "shipCity";
        public static final String shippedDate = "shippedDate";
        public static final String shipRegion = "shipRegion";
        public static final String username = "username";
        public static final String orderDetailList = "orderDetailList";
    }
}
