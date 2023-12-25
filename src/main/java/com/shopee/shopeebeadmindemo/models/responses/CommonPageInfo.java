package com.shopee.shopeebeadmindemo.models.responses;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Builder
@Data
@FieldNameConstants
public class CommonPageInfo {

    public Integer page;

    public Integer size;

    public Integer total;

    public List<Object> data;

}
