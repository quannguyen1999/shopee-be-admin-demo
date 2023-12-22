package com.shopee.shopeebeadmindemo.models.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CommonPageInfo {

    public Integer page;

    public Integer size;

    public Integer total;

    public List<Object> data;

}
