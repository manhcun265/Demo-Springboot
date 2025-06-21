package com.manhjava.demo.exception;

// Ngoại lệ tuỳ chỉnh cho ứng dụng, kế thừa từ RuntimeException
public class AppException extends RuntimeException {

    // Thuộc tính lưu mã lỗi
    private ErrorCode errorCode;

    // Constructor nhận vào ErrorCode, truyền message lên lớp cha
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // Getter cho errorCode
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    // Setter cho errorCode
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
