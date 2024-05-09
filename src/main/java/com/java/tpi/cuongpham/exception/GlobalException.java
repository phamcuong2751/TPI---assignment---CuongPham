package com.java.tpi.cuongpham.exception;

public class GlobalException extends RuntimeException{
    public GlobalException(int code, String msg) {
        super(msg);
    }
}
