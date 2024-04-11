package com.shopee.ecommer.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.json.JSONPropertyName;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class TokenResponseDto {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "expires_in")
    private Integer expiresIn;

}
