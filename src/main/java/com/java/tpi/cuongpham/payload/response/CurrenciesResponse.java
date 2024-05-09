package com.java.tpi.cuongpham.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrenciesResponse {
    private String code;
    private String name;
    private String symbol;
    private String rate;
    private float rate_float;
    private String chartName;
    private String createAt;
    private String updateAt;
}
