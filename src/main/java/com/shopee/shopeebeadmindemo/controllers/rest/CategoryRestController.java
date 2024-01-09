package com.shopee.shopeebeadmindemo.controllers.rest;

import com.shopee.shopeebeadmindemo.constants.PathApi;
import com.shopee.shopeebeadmindemo.models.hateoas.CategoryAssembler;
import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.services.CategoryService;
import com.shopee.shopeebeadmindemo.services.ReportService;
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
@RequestMapping(value = PathApi.CATEGORY)
@AllArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;

    private final ReportService reportService;

    private final CategoryAssembler categoryAssembler;

    @RequestMapping(value = PathApi.INFO_PATH, method = RequestMethod.GET)
    public ResponseEntity<EntityModel<CommonPageInfo<CategoryResponseDto>>> getInfoPath() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryAssembler.toModel(CommonPageInfo.<CategoryResponseDto>builder().build()));
    }

    @RequestMapping(value = PathApi.LIST, method = RequestMethod.POST)
    public ResponseEntity<EntityModel<CommonPageInfo<CategoryResponseDto>>> getList(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryAssembler.toModel(categoryService.getList(null, categoryRequestDto)));
    }

    @RequestMapping(value = PathApi.CREATE, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.create(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @RequestMapping(value = PathApi.EXPORT, method = RequestMethod.POST)
    public ResponseEntity<byte[]> export(@RequestBody CategoryRequestDto categoryRequestDto) {
        List<HashMap<String, Object>> listResult = categoryService.getListWithResultMap(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(reportService.printReport(listResult, categoryService.getListField(categoryRequestDto)));
    }
}
