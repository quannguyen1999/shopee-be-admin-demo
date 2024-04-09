package com.shopee.ecommer.controllers.graphql;

import com.shopee.ecommer.models.requests.CategoryRequestDto;
import com.shopee.ecommer.models.responses.CategoryResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.services.CategoryService;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import static com.shopee.ecommer.constants.AuthorityConstant.ANONYMOUS;

@Tag(
        name = "Graphql Category",
        description = "List, Export Category details"
)
@Controller
@AllArgsConstructor
public class CategoryGraphController {
    private final CategoryService categoryService;

    private final ReportService reportService;

    @PreAuthorize("hasPermission(#id, '"+ ANONYMOUS +"', '"+ ANONYMOUS +"')")
    @QueryMapping
    public CommonPageInfo<CategoryResponseDto> listCategory(@Argument CategoryRequestDto categoryRequestDto,
                                                            DataFetchingEnvironment environment) {
        return categoryService.getList(GraphQLUtils.getNameFieldGraphQL(environment), categoryRequestDto);
    }

}
