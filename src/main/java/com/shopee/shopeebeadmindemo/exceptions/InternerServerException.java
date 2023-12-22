package com.shopee.shopeebeadmindemo.exceptions;

public class InternerServerException extends RuntimeException {

    public InternerServerException(String exception) {
        super(exception);
    }
}