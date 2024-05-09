package com.java.tpi.cuongpham.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrenciesRequest {
    @NotEmpty
    private String code;
    private String name;
    private String symbol;
    private String rate;
    private float rateFloat;
    private LocalDateTime rateTime;
    private String chartName;
}
