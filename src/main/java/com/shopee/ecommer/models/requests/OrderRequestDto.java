package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.entities.Account;
import com.shopee.ecommer.entities.OrderDetail;
import com.shopee.ecommer.models.CommonBaseModel;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldNameConstants
@NoArgsConstructor
public class OrderRequestDto extends CommonBaseModel {

    private String id;

    private String orderDate;

    private String shipCity;

    private String shippedDate;

    private String shipRegion;

    private String username;

    private List<OrderDetailRequestDto> orderDetailRequestDtoList = new ArrayList<>();

    private String orderFromDate;

    private String orderToDate;

    private String shippedFromDate;

    private String shippedToDate;

    public String createFromDate;

    public String createToDate;
}
