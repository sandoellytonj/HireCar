package com.hirecar.exceptionCustom;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class ExceptionCustom extends Exception{

    public static void verify(boolean expression, HttpStatus status, String msg) {

        if (expression) {
            throw new ResponseStatusException(status, msg) {

            };
        }
    }
}
