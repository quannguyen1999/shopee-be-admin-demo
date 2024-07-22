package com.shopee.ecommer.utils;

import com.shopee.ecommer.validators.CommonValidator;
import lombok.experimental.UtilityClass;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.shopee.ecommer.constants.MessageErrors.DATE_INVALID;

@UtilityClass
public class FunctionUtils {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private final SimpleDateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd");

    //IsActive -> is_active
    public Function<String, String> camelCaseToSnakeCase() {
        return (input) -> {
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
        };
    }

    //Convert List to FIELD SORT
    public String handlerListSort(List<Map<String, String>> listSorted) {
        if (ObjectUtils.isEmpty(listSorted)) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (Map<String, String> dataSorts : listSorted) {
            result.append(FunctionUtils.camelCaseToSnakeCase().apply(dataSorts.get("field"))).append(" ").append(dataSorts.get("value"));
        }
        return result.toString();
    }

    public Function<String, Date> parseStringToDate() {
        return (input) -> {
            try {
                return df.parse(input);
            } catch (ParseException e) {
                CommonValidator.badRequest().accept(DATE_INVALID);
                throw new RuntimeException(e);
            }
        };
    }

    public Function<String, Date> parseStringToDateTime() {
        return (input) -> {
            try {
                return dfTime.parse(input);
            } catch (ParseException e) {
                CommonValidator.badRequest().accept(DATE_INVALID);
                throw new RuntimeException(e);
            }
        };
    }

    public Function<Date, String> parseDateToString() {
        return df::format;
    }

}
