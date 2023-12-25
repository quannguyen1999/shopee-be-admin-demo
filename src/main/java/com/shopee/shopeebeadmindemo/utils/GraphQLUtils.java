package com.shopee.shopeebeadmindemo.utils;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.shopee.shopeebeadmindemo.models.responses.CommonPageInfo.Fields.*;

public class GraphQLUtils {
    public static Map<String, String> getNameFieldGraphQL(DataFetchingEnvironment fetchingEnvironment) {
        return fetchingEnvironment.getSelectionSet()
                .getFields()
                .stream()
                .map(SelectedField::getName)
                .filter(name -> !Arrays.asList(page, size, total, data)
                        .contains(name))
                .collect(Collectors.toMap(
                        value -> value,
                        value -> value
                ));
    }
}
