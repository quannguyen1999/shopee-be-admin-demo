package com.shopee.ecommer.models.hateoas;

import com.shopee.ecommer.controllers.rest.ProductRestController;
import com.shopee.ecommer.models.requests.ProductRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.ProductResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductAssembler implements RepresentationModelAssembler<CommonPageInfo<ProductResponseDto>, EntityModel<CommonPageInfo<ProductResponseDto>>> {

    private static final ProductRequestDto productRequestDto = new ProductRequestDto();

    private static final List<Link> links = Arrays.asList(
            linkCreateProduct(),
            linkGetListProduct()
    );

    @Override
    public EntityModel<CommonPageInfo<ProductResponseDto>> toModel(CommonPageInfo<ProductResponseDto> entity) {
        return EntityModel.of(entity, links);
    }

    private static Link linkCreateProduct() {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductRestController.class)
                .create(ProductAssembler.productRequestDto)).withSelfRel();
    }

    private static Link linkGetListProduct() {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductRestController.class)
                .getList(ProductAssembler.productRequestDto)).withSelfRel();
    }
}
