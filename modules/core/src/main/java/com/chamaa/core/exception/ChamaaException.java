package com.chamaa.core.exception;

public class ChamaaException extends RuntimeException {

    private final String code;
    private final int statusCode;

    public ChamaaException(String code, String message, int statusCode) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
