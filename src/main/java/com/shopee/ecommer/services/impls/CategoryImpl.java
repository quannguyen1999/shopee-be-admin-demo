package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.entities.Category;
import com.shopee.ecommer.mappers.CategoryMapper;
import com.shopee.ecommer.models.requests.CategoryRequestDto;
import com.shopee.ecommer.models.responses.CategoryResponseDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.mybatis.CategoryBatisService;
import com.shopee.ecommer.repositories.CategoryRepository;
import com.shopee.ecommer.services.CategoryService;
import com.shopee.ecommer.validators.CategoryValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.shopee.ecommer.models.responses.CategoryResponseDto.Fields.*;
import static com.shopee.ecommer.utils.FunctionUtils.handlerListSort;

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
    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        //Validate
        categoryValidator.validateCreateCategory(categoryRequestDto);
        //Save
        Category category = categoryRepository.save(buildCategoryFromRequestDto(categoryRequestDto));
        //Map to Response
        return CategoryMapper.MAPPER.categoryToCategoryResponseDto(category);
    }

    private Category buildCategoryFromRequestDto(CategoryRequestDto categoryRequestDto) {
        return Category.builder()
                .id(UUID.randomUUID())
                .name(categoryRequestDto.getName())
                .build();
    }

    @Override
    public CommonPageInfo<CategoryResponseDto> getList(Map<String, String> listFieldRequest, CategoryRequestDto categoryRequestDto) {
        categoryRequestDto.setListStringSorted(handlerListSort(categoryRequestDto.getListSorted()));
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
        categoryValidator.validateListFieldRequest(categoryRequestDto);
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
