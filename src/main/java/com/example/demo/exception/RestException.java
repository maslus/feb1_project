package com.example.demo.exception;

import javax.ws.rs.core.Response;

public class RestException extends RuntimeException {
    protected int errorCode;

    public RestException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RestException(Response.Status status, String message) {
        super(message);
        this.errorCode = status.getStatusCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
