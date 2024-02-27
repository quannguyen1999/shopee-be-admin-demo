package com.shopee.ecommer.models.requests;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class EmailDto {

    private List<String> emailsFrom;

    private List<String> emailsTo;

    private String content;

    private String subject;

}
