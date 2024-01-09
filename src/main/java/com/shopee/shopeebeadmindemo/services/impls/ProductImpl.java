package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.entities.Product;
import com.shopee.shopeebeadmindemo.mappers.ProductMapper;
import com.shopee.shopeebeadmindemo.models.requests.ProductRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.models.responses.ProductResponseDto;
import com.shopee.shopeebeadmindemo.mybatis.ProductBatisService;
import com.shopee.shopeebeadmindemo.repositories.ProductRepository;
import com.shopee.shopeebeadmindemo.services.ProductService;
import com.shopee.shopeebeadmindemo.validators.ProductValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.shopee.shopeebeadmindemo.models.responses.ProductResponseDto.Fields.*;

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
    public void createProduct(ProductRequestDto productRequestDto) {
        productValidator.validateCreateProduct(productRequestDto);

        //TODO implement later
        productRepository.save(Product.builder()
                .name(productRequestDto.getName())
                .quantity(productRequestDto.getQuantity())
                .price(productRequestDto.getPrice())
                .discount(productRequestDto.getDiscount())
                .build());
    }

    @Override
    public CommonPageInfo<ProductResponseDto> getList(Map<String, String> listFieldRequest, ProductRequestDto productRequestDto) {
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
