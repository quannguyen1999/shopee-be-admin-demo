package com.shopee.shopeebeadmindemo.models.responses;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Builder
@Data
@FieldNameConstants
public class CommonPageInfo<T> {

    public Integer page;

    public Integer size;

    public Integer total;

    public List<T> data;

    public String __typename;

}
