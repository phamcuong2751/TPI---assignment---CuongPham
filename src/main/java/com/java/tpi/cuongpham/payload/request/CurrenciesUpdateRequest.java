package com.java.tpi.cuongpham.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CurrenciesUpdateRequest {
    @NotEmpty
    private String code;
    private String name;
    private String symbol;
    private String rate;
    private float rateFloat;
    private String chartName;
}
