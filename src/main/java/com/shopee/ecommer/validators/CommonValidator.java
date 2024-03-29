package com.shopee.ecommer.validators;

import com.shopee.ecommer.constants.MessageErrors;
import com.shopee.ecommer.exceptions.BadRequestException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CommonValidator {

    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public static final Date BIRTHDAY_LIMIT = new Date(2023 - 1900, Calendar.JANUARY, 1);

    static BiConsumer<Object, MessageErrors> checkEmpty() {
        return (input, messageError) -> {
            if (ObjectUtils.isEmpty(input) || (input instanceof String && !StringUtils.hasLength(input.toString()))
            ) {
                badRequest().accept(messageError);
            }
        };
    }

    static BiConsumer<String, MessageErrors> checkUUID() {
        return (input, messageError) -> {
            try {
                // Try to create a UUID object from the given string
                UUID uuid = UUID.fromString(input);
            } catch (IllegalArgumentException e) {
                badRequest().accept(messageError);
            }
        };
    }

    static BiConsumer<Boolean, MessageErrors> checkCondition() {
        return (input, messageError) -> {
            if (input) {
                badRequest().accept(messageError);
            }
        };
    }

    static BiConsumer<Object, MessageErrors> checkIsExists() {
        return (input, messageError) -> {
            if (!ObjectUtils.isEmpty(input)
            ) {
                badRequest().accept(messageError);
            }
        };
    }

    static BiConsumer<Object, MessageErrors> checkIsNotExists() {
        return (input, messageError) -> {
            if (ObjectUtils.isEmpty(input)
            ) {
                badRequest().accept(messageError);
            }
        };
    }

    static BiConsumer<Double, MessageErrors> checkDouble() {
        return (input, messageError) -> {
            checkEmpty().accept(input, messageError);
            if (input < 0) {
                badRequest().accept(messageError);
            }
        };
    }

    static BiConsumer<List<String>, MessageErrors> checkList() {
        return (input, messageError) -> {
            if (CollectionUtils.isEmpty(input) || input.size() == 0) {
                badRequest().accept(messageError);
            }
        };
    }

    static Consumer<MessageErrors> badRequest() {
        return messageErrors -> {
            throw new BadRequestException(messageErrors);
        };
    }


}
