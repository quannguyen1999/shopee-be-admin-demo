package com.shopee.ecommer.services.impls;

import com.shopee.ecommer.models.responses.CommonPageInfo;
import com.shopee.ecommer.utils.FunctionUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.shopee.ecommer.models.responses.CommonPageInfo.Fields.total;

public class AdapterImpl {

    static List<String> convertListFieldRequest(Map<String, String> listFieldRequest, List<String> listDefaults) {
        return CollectionUtils.isEmpty(listFieldRequest) ? listDefaults : new ArrayList<>(listFieldRequest.values());
    }

    //Current Value, Default Value, Return Value
    static BiFunction<List<String>, List<String>, List<String>> checkList() {
        return (current, defaultValue) -> CollectionUtils.isEmpty(current) ? defaultValue : current;
    }

    //List Result from My Batis, Total Page
    static Function<List<HashMap<String, Object>>, Integer> getCommonTotalPage() {
        return maps -> maps.stream()
                .map(t -> checkAndGetDefaultValue().apply(t))
                .findFirst()
                .orElse(0);
    }

    //CommonPageInfo, List Return
    static <T> Predicate<CommonPageInfo<T>> checkPageSize() {
        return value -> !ObjectUtils.isEmpty(value.getPage()) && !ObjectUtils.isEmpty(value.getSize()) && (value.getPage() * value.getSize()) < value.getTotal();
    }

    static Function<Map<String, Object>, Integer> checkAndGetDefaultValue() {
        return t -> Integer.parseInt(t.getOrDefault(total, "0").toString());
    }

}
