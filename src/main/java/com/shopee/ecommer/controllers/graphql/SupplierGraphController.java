package com.shopee.ecommer.controllers.graphql;

import com.shopee.ecommer.constants.AuthorityConstant;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.SupplierResponseDto;
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
        name = "Graphql Supplier",
        description = "List, Export Supplier details"
)
@Controller
@AllArgsConstructor
public class SupplierGraphController {
    private final SupplierService supplierService;

    private final ReportService reportService;

    @PreAuthorize("hasAuthority('" + AuthorityConstant.ADMIN + "')")
    @QueryMapping
    public CommonPageInfo<SupplierResponseDto> listSupplier(@Argument SupplierRequestDto supplierRequestDto, DataFetchingEnvironment environment) {
        return supplierService.getList(GraphQLUtils.getNameFieldGraphQL(environment), supplierRequestDto);
    }

}
