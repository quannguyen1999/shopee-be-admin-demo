package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.entities.Product;
import com.shopee.ecommer.mappers.CategoryMapper;
import com.shopee.ecommer.mappers.ProductMapper;
import com.shopee.ecommer.models.requests.CategoryRequestDto;
import com.shopee.ecommer.models.requests.ProductRequestDto;
import com.shopee.ecommer.models.responses.CategoryResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.ProductResponseDto;
import com.shopee.ecommer.mybatis.ProductBatisService;
import com.shopee.ecommer.repositories.ProductRepository;
import com.shopee.ecommer.services.ProductService;
import com.shopee.ecommer.validators.ProductValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.shopee.ecommer.models.responses.ProductResponseDto.Fields.*;
import static com.shopee.ecommer.utils.FunctionUtils.handlerListSort;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImpl extends AdapterImpl implements ProductService {
    protected final ProductRepository productRepository;

    protected final ProductBatisService productBatisService;

    protected final ProductValidator productValidator;

    public static List<String> getAllListDefault() {
        return new ArrayList<>(Arrays.asList(id, name, image, quantity, price, discount));
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        //Validate
        productValidator.validateCreateProduct(productRequestDto);
        //Save
        Product product = productRepository.save(buildProductFromRequestDto(productRequestDto));
        //Map to Response
        return ProductMapper.MAPPER.productToProductResponseDto(product);
    }

    private Product buildProductFromRequestDto(ProductRequestDto productRequestDto) {
        return Product.builder().id(UUID.randomUUID())
                .name(productRequestDto.getName())
                .quantity(productRequestDto.getQuantity())
                .price(productRequestDto.getPrice())
                .discount(productRequestDto.getDiscount())
                .build();
    }

    @Override
    public CommonPageInfo<ProductResponseDto> getList(Map<String, String> listFieldRequest, ProductRequestDto productRequestDto) {
        productRequestDto.setListStringSorted(handlerListSort(productRequestDto.getListSorted()));
        productRequestDto.setListFields(convertListFieldRequest(listFieldRequest, getAllListDefault()));
        productRequestDto.setTotalRecord(getCommonTotalPage().apply(productBatisService.getList(productRequestDto, true)));
        return CommonPageInfo.<ProductResponseDto>builder()
                .page(productRequestDto.getPage())
                .size(productRequestDto.getSize())
                .total(productRequestDto.getTotalRecord())
                .data(handlerList(productRequestDto))
                .build();
    }

    @Override
    public List<HashMap<String, Object>> getListWithResultMap(ProductRequestDto productRequestDto) {
        productValidator.validateListFieldRequest(productRequestDto);
        return productBatisService.getList(productRequestDto, false);
    }

    @Override
    public List<String> getListField(ProductRequestDto productRequestDto) {
        return checkList().apply(productRequestDto.getListFields(), AccountImpl.getAllListDefault());
    }

    private List<ProductResponseDto> handlerList(ProductRequestDto productRequestDto) {
        return checkPageSize().test(CommonPageInfo.builder()
                .page(productRequestDto.getPage())
                .size(productRequestDto.getSize())
                .total(productRequestDto.getTotalRecord())
                .build()) ? productBatisService.getList(productRequestDto, false)
                .stream().map(ProductMapper.MAPPER::mapToProductResponseDto).toList() : new ArrayList<>();
    }
}
