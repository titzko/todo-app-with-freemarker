package com.titzko.freemarkertodo.exceptions;

public class InvalidTaskException extends RuntimeException {

    public InvalidTaskException(String msg) {
        super(msg);
    }
}
