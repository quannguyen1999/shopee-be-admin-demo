package com.shopee.ecommer.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestTestDto {

    public DocumentEnum documentEnum;

    public Object contentType;

}
