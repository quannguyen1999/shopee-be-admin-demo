package com.shopee.ecommer.controllers.rest;

import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.hateoas.AccountAssembler;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.requests.Oauth2ClientDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.services.AccountService;
import com.shopee.ecommer.services.ReportService;
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

@RestController
@RequestMapping(value = PathApi.ACCOUNT)
@AllArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    private final ReportService reportService;

    private final AccountAssembler accountAssembler;

    @RequestMapping(value = PathApi.GET_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<Object> getToken(Oauth2ClientDto oauth2ClientDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getToken(oauth2ClientDto));
    }

    @RequestMapping(value = PathApi.GET_REFRESH_TOKEN, method = RequestMethod.POST)
    public ResponseEntity<Object> getRefreshToken(Oauth2ClientDto oauth2ClientDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.refreshToken(oauth2ClientDto));
    }

    @RequestMapping(value = PathApi.INFO_PATH, method = RequestMethod.GET)
    public ResponseEntity<EntityModel<CommonPageInfo<AccountResponseDto>>> getInfoPath() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountAssembler.toModel(CommonPageInfo.<AccountResponseDto>builder().build()));
    }

    @RequestMapping(value = PathApi.LIST, method = RequestMethod.POST)
    public ResponseEntity<EntityModel<CommonPageInfo<AccountResponseDto>>> getListAccounts(
            @RequestBody AccountRequestDto accountRequestDto, Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountAssembler.toModel(accountService.getList(null, accountRequestDto)));
    }

    @RequestMapping(value = PathApi.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.createAccount(accountRequestDto));
    }

    @RequestMapping(value = PathApi.PUT, method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.updateAccount(accountRequestDto));
    }

    @RequestMapping(value = PathApi.EXPORT, method = RequestMethod.POST)
    public ResponseEntity<byte[]> exportAccount(@RequestBody AccountRequestDto accountRequestDto) {
        List<HashMap<String, Object>> listResult = accountService.getListWithResultMap(accountRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.printReport(listResult, accountService.getListField(accountRequestDto)));
    }


}
