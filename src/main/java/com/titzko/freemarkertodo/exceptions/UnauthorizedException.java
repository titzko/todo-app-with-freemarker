package com.titzko.freemarkertodo.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
