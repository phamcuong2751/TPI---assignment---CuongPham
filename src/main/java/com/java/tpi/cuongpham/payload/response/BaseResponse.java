package com.java.tpi.cuongpham.payload.response;

import com.google.gson.annotations.SerializedName;
import com.java.tpi.cuongpham.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {
    private static final long serializableUID = 1L;
    @SerializedName("status_code")
    private int status_code = 200;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;
    @SerializedName("request_time")
    private String request_time = DateUtils.convertLocalTime(LocalDateTime.now());

    public BaseResponse(int status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public BaseResponse(int status_code, String message, T data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }
}