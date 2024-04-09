package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.entities.Supplier;
import com.shopee.ecommer.mappers.SupplierMapper;
import com.shopee.ecommer.models.requests.SupplierRequestDto;
import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.models.responses.SupplierResponseDto;
import com.shopee.ecommer.mybatis.SupplierBatisService;
import com.shopee.ecommer.repositories.SupplierRepository;
import com.shopee.ecommer.services.SupplierService;
import com.shopee.ecommer.validators.SupplierValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.shopee.ecommer.models.responses.SupplierResponseDto.Fields.*;
import static com.shopee.ecommer.utils.FunctionUtils.handlerListSort;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SupplierImpl extends AdapterImpl implements SupplierService {

    protected final SupplierRepository supplierRepository;

    protected final SupplierValidator supplierValidator;

    protected final SupplierBatisService supplierBatisService;

    public static List<String> getAllListDefault() {
        return new ArrayList<>(Arrays.asList(id, phone, address, companyName));
    }


    @Override
    public SupplierResponseDto createSupplier(SupplierRequestDto supplierRequestDto) {
        //Validate
        supplierValidator.validateCreateSupplier(supplierRequestDto);
        //Save
        Supplier supplier = supplierRepository.save(buildSupplierFromRequestDto(supplierRequestDto));
        //Map to Response
        return SupplierMapper.MAPPER.supplierToSupplierResponseDto(supplier);
    }

    private Supplier buildSupplierFromRequestDto(SupplierRequestDto supplierRequestDto) {
        return Supplier.builder().id(UUID.randomUUID())
                .phone(supplierRequestDto.getPhone())
                .address(supplierRequestDto.getAddress())
                .companyName(supplierRequestDto.getCompanyName())
                .build();
    }

    @Override
    public SupplierResponseDto updateSupplier(SupplierRequestDto data) {
        //Validator
        supplierValidator.validateUpdateSupplier(data);

        //Update
        Supplier supplier = supplierRepository.findById(UUID.fromString(data.getId())).get();
        supplier.setPhone(data.getPhone());
        supplier.setAddress(data.getAddress());
        supplier.setCompanyName(data.getCompanyName());

        return SupplierMapper.MAPPER.supplierToSupplierResponseDto(supplierRepository.save(supplier));
    }

    @Override
    public CommonPageInfo<SupplierResponseDto> getList(Map<String, String> listFields, SupplierRequestDto data) {
        data.setListStringSorted(handlerListSort(data.getListSorted()));
        data.setListFields(convertListFieldRequest(listFields, getAllListDefault()));
        data.setTotalRecord(getCommonTotalPage().apply(supplierBatisService.getList(data, true)));
        return CommonPageInfo.<SupplierResponseDto>builder()
                .page(data.getPage())
                .size(data.getSize())
                .total(data.getTotalRecord())
                .data(handlerList(data))
                .build();
    }

    @Override
    public List<HashMap<String, Object>> getListWithResultMap(SupplierRequestDto supplierRequestDto) {
        supplierValidator.validateListFieldRequest(supplierRequestDto);
        return supplierBatisService.getList(supplierRequestDto, false);
    }

    @Override
    public List<String> getListField(SupplierRequestDto data) {
        return checkList().apply(data.getListFields(), SupplierImpl.getAllListDefault());
    }

    private List<SupplierResponseDto> handlerList(SupplierRequestDto data) {
        return checkPageSize().test(CommonPageInfo.builder()
                .page(data.getPage())
                .size(data.getSize())
                .total(data.getTotalRecord())
                .build()) ? supplierBatisService.getList(data, false)
                .stream().map(SupplierMapper.MAPPER::mapToSupplierResponseDto).toList() : new ArrayList<>();
    }
}
