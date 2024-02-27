package com.shopee.ecommer.controllers.graphql;

import com.shopee.ecommer.models.requests.ProductRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.ProductResponseDto;
import com.shopee.ecommer.services.ProductService;
import com.shopee.ecommer.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Tag(
        name = "Graphql Product",
        description = "List, Export Product details"
)
@Controller
@AllArgsConstructor
public class ProductGraphController {
    private final ProductService productService;

    @QueryMapping
    public CommonPageInfo<ProductResponseDto> listProduct(@Argument ProductRequestDto productRequestDto,
                                                          DataFetchingEnvironment environment) {
        return productService.getList(GraphQLUtils.getNameFieldGraphQL(environment), productRequestDto);
    }

}
