package com.shopee.shopeebeadmindemo.exceptions;

import com.shopee.shopeebeadmindemo.constants.MessageErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(MessageErrors exception) {
        super(exception.toString());
    }

}