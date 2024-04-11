package com.shopee.ecommer.controllers.rest;

import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.hateoas.AccountAssembler;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.TestDto;
import com.shopee.ecommer.services.AccountService;
import com.shopee.ecommer.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
@Tag(
        name = "API Rest Account",
        description = "Get Token, CRUD, Export API Account details"
)
@RestController
@RequestMapping(value = PathApi.ACCOUNT)
@AllArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    private final ReportService reportService;

    private final AccountAssembler accountAssembler;

    @Operation(
            summary = "Get Token",
            description = "Get Token by 2 param code + redirectUrl"
    )
    @RequestMapping(value = PathApi.GET_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<Object> getToken(Oauth2ClientDto oauth2ClientDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getToken(oauth2ClientDto));
    }

    @Operation(
            summary = "Get test",
            description = "Get Token by 2 param code + redirectUrl"
    )
    @RequestMapping(value = PathApi.TEST, method = RequestMethod.POST)
    public ResponseEntity<TestDto> test(@RequestBody TestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.test(accountRequestDto));
    }

    @Operation(
            summary = "Get Refresh Token",
            description = "Get Refresh Token by 1 param refreshToken"
    )
    @RequestMapping(value = PathApi.GET_REFRESH_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<Object> getRefreshToken(Oauth2ClientDto oauth2ClientDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.refreshToken(oauth2ClientDto));
    }

    @Operation(
            summary = "Get Info Account",
            description = "Get Info Account by authen"
    )
    @RequestMapping(value = PathApi.GET_INFO, method = RequestMethod.GET)
    public ResponseEntity<AccountResponseDto> getInfo(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getInfo(authentication));
    }

    @Operation(
            summary = "Get Link reference of API Account"
    )
    @RequestMapping(value = PathApi.INFO_PATH, method = RequestMethod.GET)
    public ResponseEntity<EntityModel<CommonPageInfo<AccountResponseDto>>> getInfoPath() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountAssembler.toModel(CommonPageInfo.<AccountResponseDto>builder().build()));
    }

    @Operation(
            summary = "Get List Account"
    )
    @RequestMapping(value = PathApi.LIST, method = RequestMethod.POST)
    public ResponseEntity<EntityModel<CommonPageInfo<AccountResponseDto>>> getListAccounts(
            @RequestBody AccountRequestDto accountRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountAssembler.toModel(accountService.getList(null, accountRequestDto)));
    }

    @Operation(
            summary = "Create Account"
    )
    @RequestMapping(value = PathApi.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.createAccount(accountRequestDto));
    }

    @Operation(
            summary = "Registered Account"
    )
    @RequestMapping(value = PathApi.REGISTER, method = RequestMethod.POST)
    public ResponseEntity<?> registerAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.registeredAccount(accountRequestDto));
    }

    @Operation(
            summary = "Update Account"
    )
    @RequestMapping(value = PathApi.PUT, method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.updateAccount(accountRequestDto));
    }

    @Operation(
            summary = "Export Account"
    )
    @RequestMapping(value = PathApi.EXPORT, method = RequestMethod.POST)
    public ResponseEntity<byte[]> exportAccount(@RequestBody AccountRequestDto accountRequestDto) {
        List<HashMap<String, Object>> listResult = accountService.getListWithResultMap(accountRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.printReport(listResult, accountService.getListField(accountRequestDto)));
    }


}
