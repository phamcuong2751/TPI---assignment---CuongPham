package com.java.tpi.cuongpham.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CurrenciesInsertRequest {
    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
    @NotEmpty
    private String symbol;
    @NotEmpty
    private String rate;
    @NotNull
    private float rateFloat;
    private String chartName;
}
