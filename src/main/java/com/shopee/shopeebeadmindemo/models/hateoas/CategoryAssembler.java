package com.shopee.shopeebeadmindemo.models.hateoas;

import com.shopee.shopeebeadmindemo.controllers.rest.CategoryRestController;
import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryAssembler implements RepresentationModelAssembler<CommonPageInfo<CategoryResponseDto>, EntityModel<CommonPageInfo<CategoryResponseDto>>> {

    private static final CategoryRequestDto categoryRequestDto = new CategoryRequestDto();


    private static final List<Link> links = Arrays.asList(
            linkCreate(),
            linkGetList()
    );

    @Override
    public EntityModel<CommonPageInfo<CategoryResponseDto>> toModel(CommonPageInfo<CategoryResponseDto> entity) {
        return EntityModel.of(entity, links);
    }

    private static Link linkCreate() {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryRestController.class)
                .create(CategoryAssembler.categoryRequestDto)).withSelfRel();
    }

    private static Link linkGetList() {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CategoryRestController.class)
                .getList(CategoryAssembler.categoryRequestDto)).withSelfRel();
    }
}
