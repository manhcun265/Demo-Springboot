package com.manhjava.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

// Enum định nghĩa các mã lỗi và thông báo lỗi cho ứng dụng
@Getter
public enum ErrorCode {
    // Lỗi không xác định
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    // Lỗi key không hợp lệ
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    // Lỗi user đã tồn tại
    USER_EXITSTED(1002, "User already exists", HttpStatus.BAD_REQUEST),
    // Lỗi username không hợp lệ
    USERNAME_INVALID(1003, "Username must be at least 3 characters long", HttpStatus.BAD_REQUEST),
    // Lỗi password không hợp lệ
    INVALID_PASSWORD(1004, "Password must be at least 8 characters long", HttpStatus.BAD_REQUEST),
    // Lỗi user không tồn tại
    USER_NOT_EXITSTED(1005, "User does not exist", HttpStatus.NOT_FOUND),
    // Lỗi chưa xác thực
    UNAUTHENTICATED(1006, "User is not authenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    ;
    // Mã lỗi
    private int code;
    // Thông báo lỗi
    private String message;
    private HttpStatusCode statusCode;

    // Constructor cho enum
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}


