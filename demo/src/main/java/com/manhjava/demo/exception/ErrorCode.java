package com.manhjava.demo.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXITSTED(1002, "User already exists"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters long"),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters long"),
    USER_NOT_EXITSTED(1005, "User does not exist"),
    UNAUTHENTICATED(1006, "User is not authenticated"),
    ;
    private int code;
    private String message;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
