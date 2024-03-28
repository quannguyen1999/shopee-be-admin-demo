package com.shopee.ecommer.models.requests;

import com.shopee.ecommer.entities.Account;
import com.shopee.ecommer.models.CommonBaseModel;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.Date;
import java.util.UUID;

@Data
@FieldNameConstants
@NoArgsConstructor
public class OrderRequestDto extends CommonBaseModel {

    private String id;

    private String orderDate;

    private String shipCity;

    private String shippedDate;

    private String shipRegion;

    private String userName;

}
