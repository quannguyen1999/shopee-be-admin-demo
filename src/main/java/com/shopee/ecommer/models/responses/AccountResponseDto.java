package com.shopee.ecommer.models.responses;

import com.shopee.ecommer.models.CommonBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Schema(
        name = "Account"
)
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AccountResponseDto extends CommonBaseModel {

    @Schema(
            description = "Id of account", example = "FCZEPNMIYM6SEE7Z2VWGEFQ5AVMKBDVW"
    )
    public UUID id;

    @Schema(
            description = "Username of account", example = "client_1"
    )
    public String username;

    @Schema(
            description = "Birthday of account", example = "client_1"
    )
    public String birthday;

    @Schema(
            description = "Birthday of account", example = "male"
    )
    public Boolean gender;

    @Schema(
            description = "Email of account", example = "abc@gmail.com"
    )
    public String email;

    @Schema(
            description = "Link Of Avatar", example = "abc@gmail.com"
    )
    public String avatar;

    @Schema(
            description = "Is Account Enable to User", example = "true"
    )
    public Boolean isActive;

    @Schema(
            description = "Enable Mfa To verify in authen app", example = "true"
    )
    public Boolean mfaEnabled;

    @Schema(
            description = "Check Is Current Acount is register mfa", example = "true"
    )
    public Boolean mfaRegistered;

    public static final class Fields {
        public static final String id = "id";
        public static final String username = "username";
        public static final String birthday = "birthday";
        public static final String gender = "gender";
        public static final String email = "email";
        public static final String avatar = "avatar";
        public static final String isActive = "isActive";
        public static final String mfaEnabled = "mfaEnabled";
        public static final String mfaRegistered = "mfaRegistered";
    }
}
