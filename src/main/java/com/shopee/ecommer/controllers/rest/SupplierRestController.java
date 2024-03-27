package com.shopee.ecommer.controllers.rest;

import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.SupplierResponseDto;
import com.shopee.ecommer.services.ReportService;
import com.shopee.ecommer.services.SupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Tag(
        name = "API Rest Supplier",
        description = "CRUD, Export API Supplier details"
)
@RestController
@RequestMapping(value = PathApi.SUPPLIER)
@AllArgsConstructor
public class SupplierRestController {

    private final SupplierService supplierService;

    private final ReportService reportService;


    @RequestMapping(value = PathApi.LIST, method = RequestMethod.POST)
    public ResponseEntity<CommonPageInfo<SupplierResponseDto>> getList(@RequestBody SupplierRequestDto supplierRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(supplierService.getList(null, supplierRequestDto));
    }

    @RequestMapping(value = PathApi.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SupplierRequestDto supplierRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.createSupplier(supplierRequestDto));
    }

    @RequestMapping(value = PathApi.EXPORT, method = RequestMethod.POST)
    public ResponseEntity<byte[]> export(@RequestBody SupplierRequestDto supplierRequestDto) {
        List<HashMap<String, Object>> listResult = supplierService.getListWithResultMap(supplierRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.printReport(listResult, supplierService.getListField(supplierRequestDto)));
    }
}
