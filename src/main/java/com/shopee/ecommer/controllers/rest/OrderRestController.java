package com.shopee.ecommer.controllers.rest;

import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.requests.OrderRequestDto;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.OrderResponseDto;
import com.shopee.ecommer.models.responses.SupplierResponseDto;
import com.shopee.ecommer.services.OrderService;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.services.SupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Tag(
        name = "API Rest Order",
        description = "CRUD, Export API Order details"
)
@RestController
@RequestMapping(value = PathApi.ORDER)
@AllArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    private final ReportService reportService;


    @RequestMapping(value = PathApi.LIST, method = RequestMethod.POST)
    public ResponseEntity<CommonPageInfo<OrderResponseDto>> getList(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getList(null, orderRequestDto));
    }

    @RequestMapping(value = PathApi.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(orderRequestDto));
    }

    @RequestMapping(value = PathApi.EXPORT, method = RequestMethod.POST)
    public ResponseEntity<byte[]> export(@RequestBody OrderRequestDto orderRequestDto) {
        List<HashMap<String, Object>> listResult = orderService.getListWithResultMap(orderRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.printReport(listResult, orderService.getListField(orderRequestDto)));
    }
}
