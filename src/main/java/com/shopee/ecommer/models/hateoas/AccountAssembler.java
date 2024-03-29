package com.shopee.ecommer.models.hateoas;

import com.shopee.ecommer.controllers.rest.AccountRestController;
import com.shopee.ecommer.models.requests.AccountRequestDto;
import com.shopee.ecommer.models.responses.AccountResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AccountAssembler implements RepresentationModelAssembler<CommonPageInfo<AccountResponseDto>, EntityModel<CommonPageInfo<AccountResponseDto>>> {

    private static final AccountRequestDto accountRequestDto = new AccountRequestDto();

    private static final List<Link> links = Arrays.asList(
            linkCreateAccount(),
            linkGetListAccount()
    );

    @Override
    public EntityModel<CommonPageInfo<AccountResponseDto>> toModel(CommonPageInfo<AccountResponseDto> entity) {
        return EntityModel.of(entity, links);
    }

    private static Link linkCreateAccount() {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountRestController.class)
                .createAccount(AccountAssembler.accountRequestDto)).withSelfRel();
    }

    private static Link linkGetListAccount() {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountRestController.class)
                .getListAccounts(AccountAssembler.accountRequestDto, null)).withSelfRel();
    }
}
