package com.shopee.ecommer.controllers.graphql;

import com.shopee.ecommer.constants.AuthorityConstant;
import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.OrderResponseDto;
import com.shopee.ecommer.models.responses.SupplierResponseDto;
import com.shopee.ecommer.services.OrderService;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.services.SupplierService;
import com.shopee.ecommer.utils.GraphQLUtils;
import graphql.schema.DataFetchingEnvironment;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Tag(
        name = "Graphql Order",
        description = "List, Export Order details"
)
@Controller
@AllArgsConstructor
public class OrderGraphController {
    private final OrderService orderService;

    private final ReportService reportService;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.ADMIN + "')")
    @QueryMapping
    public CommonPageInfo<OrderResponseDto> listOrder(@Argument OrderRequestDto orderRequestDto, DataFetchingEnvironment environment) {
        return orderService.getList(GraphQLUtils.getNameFieldGraphQL(environment), orderRequestDto);
    }

}
