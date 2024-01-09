package com.shopee.shopeebeadmindemo.controllers.graphql;

import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.services.CategoryService;
import com.shopee.shopeebeadmindemo.services.ReportService;
import com.shopee.shopeebeadmindemo.utils.GraphQLUtils;
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

//
//    @QueryMapping
//    public byte[] exportCategory(@Argument CategoryRequestDto categoryRequestDto) {
//        List<HashMap<String, Object>> listResult = categoryService.getListWithResultMap(categoryRequestDto);
//        return reportService.printReport(listResult, categoryService.getListField(categoryRequestDto));
//    }
}
