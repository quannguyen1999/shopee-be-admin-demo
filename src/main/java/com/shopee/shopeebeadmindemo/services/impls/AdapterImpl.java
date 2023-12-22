package com.shopee.shopeebeadmindemo.services.impls;

import com.shopee.shopeebeadmindemo.constants.CommonFieldValue;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterImpl {

    public List<String> getList(Map<String, String> listFields, List<String> listDefaults) {
        return CollectionUtils.isEmpty(listFields) ? listDefaults : new ArrayList<>(listFields.values());
    }

    Integer getCommonTotalPage(List<Map<String, Object>> values) {
        return values.stream().map(t -> Integer.parseInt(t.get(CommonFieldValue.FIELD_TOTAL_PAGES).toString())).findFirst().orElse(0);
    }

}
