package com.shopee.shopeebeadmindemo.services.impls;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo.Fields.total;

public class AdapterImpl {

    static List<String> getListField(Map<String, String> listFieldRequest, List<String> listDefaults) {
        return CollectionUtils.isEmpty(listFieldRequest) ? listDefaults : new ArrayList<>(listFieldRequest.values());
    }

    static Function<List<HashMap<String, Object>>, Integer> getCommonTotalPage() {
        return maps -> maps.stream()
                .map(t -> getDefaultValue().apply(t))
                .findFirst()
                .orElse(0);
    }
    
    static Function<Map<String, Object>, Integer> getDefaultValue() {
        return t -> Integer.parseInt(t.getOrDefault(total, "0").toString());
    }

}
