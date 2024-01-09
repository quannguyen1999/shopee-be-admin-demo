package com.shopee.ecommer.controllers.rest;

import com.shopee.ecommer.constants.PathApi;
import com.shopee.ecommer.models.hateoas.ProductAssembler;
import com.shopee.ecommer.models.requests.ProductRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.ProductResponseDto;
import com.shopee.ecommer.services.ProductService;
import com.shopee.ecommer.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = PathApi.PRODUCT)
@AllArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    private final ReportService reportService;

    private final ProductAssembler productAssembler;

    @RequestMapping(value = PathApi.INFO_PATH, method = RequestMethod.GET)
    public ResponseEntity<EntityModel<CommonPageInfo<ProductResponseDto>>> getInfoPath() {
        return ResponseEntity.status(HttpStatus.OK).body(productAssembler.toModel(CommonPageInfo.<ProductResponseDto>builder().build()));
    }

    @RequestMapping(value = PathApi.LIST, method = RequestMethod.POST)
    public ResponseEntity<EntityModel<CommonPageInfo<ProductResponseDto>>> getList(@RequestBody ProductRequestDto ProductRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productAssembler.toModel(productService.getList(null, ProductRequestDto)));
    }

    @RequestMapping(value = PathApi.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductRequestDto ProductRequestDto) {
        productService.createProduct(ProductRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @RequestMapping(value = PathApi.EXPORT, method = RequestMethod.POST)
    public ResponseEntity<byte[]> export(@RequestBody ProductRequestDto ProductRequestDto) {
        List<HashMap<String, Object>> listResult = productService.getListWithResultMap(ProductRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportService.printReport(listResult, productService.getListField(ProductRequestDto)));
    }
}
