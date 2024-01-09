package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.entities.Category;
import com.shopee.shopeebeadmindemo.mappers.CategoryMapper;
import com.shopee.shopeebeadmindemo.models.requests.CategoryRequestDto;
import com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto;
import com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo;
import com.shopee.shopeebeadmindemo.mybatis.CategoryBatisService;
import com.shopee.shopeebeadmindemo.repositories.CategoryRepository;
import com.shopee.shopeebeadmindemo.services.CategoryService;
import com.shopee.shopeebeadmindemo.validators.CategoryValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.shopee.shopeebeadmindemo.models.responses.CategoryResponseDto.Fields.*;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryImpl extends AdapterImpl implements CategoryService {

    protected final CategoryBatisService categoryBatisService;

    protected final CategoryRepository categoryRepository;

    protected final CategoryValidator categoryValidator;

    public static List<String> getAllListDefault() {
        return new ArrayList<>(Arrays.asList(id, name, image));
    }

    @Override
    public void create(CategoryRequestDto categoryRequestDto) {
        categoryValidator.validateCreateCategory(categoryRequestDto);

        categoryRepository.save(Category.builder()
                .name(categoryRequestDto.getName())
                .build());
    }

    @Override
    public CommonPageInfo<CategoryResponseDto> getList(Map<String, String> listFieldRequest, CategoryRequestDto categoryRequestDto) {
        categoryRequestDto.setListFields(convertListFieldRequest(listFieldRequest, getAllListDefault()));
        categoryRequestDto.setTotalRecord(getCommonTotalPage().apply(categoryBatisService.getList(categoryRequestDto, true)));
        return CommonPageInfo.<CategoryResponseDto>builder()
                .page(categoryRequestDto.getPage())
                .size(categoryRequestDto.getSize())
                .total(categoryRequestDto.getTotalRecord())
                .data(handlerList(categoryRequestDto))
                .build();
    }

    @Override
    public List<HashMap<String, Object>> getListWithResultMap(CategoryRequestDto categoryRequestDto) {
        return categoryBatisService.getList(categoryRequestDto, false);
    }

    @Override
    public List<String> getListField(CategoryRequestDto categoryRequestDto) {
        return checkList().apply(categoryRequestDto.getListFields(), AccountImpl.getAllListDefault());
    }

    private List<CategoryResponseDto> handlerList(CategoryRequestDto categoryRequestDto) {
        return checkPageSize().test(CommonPageInfo.builder()
                .page(categoryRequestDto.getPage())
                .size(categoryRequestDto.getSize())
                .total(categoryRequestDto.getTotalRecord())
                .build()) ? categoryBatisService.getList(categoryRequestDto, false)
                .stream().map(CategoryMapper.MAPPER::mapToCategoryResponseDto).toList() : new ArrayList<>();
    }

}
