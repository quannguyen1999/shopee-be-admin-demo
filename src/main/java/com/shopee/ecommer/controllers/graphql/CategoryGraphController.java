package com.shopee.ecommer.controllers.graphql;

import com.shopee.ecommer.models.requests.CategoryRequestDto;
import com.shopee.ecommer.models.responses.CategoryResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.services.CategoryService;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CategoryGraphController {
    private final CategoryService categoryService;

    private final ReportService reportService;

    @QueryMapping
    public CommonPageInfo<CategoryResponseDto> listCategory(@Argument CategoryRequestDto categoryRequestDto,
                                                            DataFetchingEnvironment environment) {
        return categoryService.getList(GraphQLUtils.getNameFieldGraphQL(environment), categoryRequestDto);
    }
    
}
