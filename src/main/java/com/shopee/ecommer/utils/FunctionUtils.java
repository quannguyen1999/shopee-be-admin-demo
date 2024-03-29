package com.shopee.ecommer.utils;

import graphql.language.StringValue;
import graphql.schema.CoercingParseLiteralException;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.shopee.ecommer.mappers.CommonUtilMapper.DATE_FORMAT;

public class FunctionUtils {

    //IsActive -> is_active
    private static String camelCaseToSnakeCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(input.charAt(0)));

        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result.append('_').append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    //Conert List to FIELD SORT
    public static String handlerListSort(List<Map<String, String>> listSorted){
        if(ObjectUtils.isEmpty(listSorted)){
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (Map<String, String> dataSorts : listSorted) {
            result.append(FunctionUtils.camelCaseToSnakeCase(dataSorts.get("field"))).append(" ").append(dataSorts.get("value"));
        }
        return result.toString();
    }

    public static Date parseDateObjectToDate(String input) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(input);
        } catch (ParseException e) {
            throw new CoercingParseLiteralException("Invalid value to parse: " + input);
        }
    }

}
