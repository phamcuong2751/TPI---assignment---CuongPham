package com.java.tpi.cuongpham.payload.response;

public class BaseResponseBuilder {
    public static <T> BaseResponse<T> build(int statusCode, String message, T data) {
        return new BaseResponse<>(statusCode, message, data);
    }

    public static <T> BaseResponse<T> build(int statusCode, String message) {
        return new BaseResponse<>(statusCode, message);
    }
}