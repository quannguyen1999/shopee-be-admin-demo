package com.shopee.shopeebeadmindemo.uitls;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;

import java.util.List;
import java.util.stream.Collectors;

public class GraphQLUtils {
    public static List<String> getNameFieldGraphQL(DataFetchingEnvironment fetchingEnvironment) {
        return fetchingEnvironment
                .getSelectionSet()
                .getFields()
                .stream().map(SelectedField::getName)
                .collect(Collectors.toList());
    }
}
