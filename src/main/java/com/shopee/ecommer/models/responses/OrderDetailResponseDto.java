package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDto extends CommonBaseModel {

    public UUID id;

    public UUID orderId;

    public UUID productId;

    public Double discount;

    public int quantity;

    public Double totalAmount;

}
