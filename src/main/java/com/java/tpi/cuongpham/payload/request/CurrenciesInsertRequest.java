package com.java.tpi.cuongpham.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

    public CurrenciesInsertRequest(String eur, String euro, String symbol, String rate, float rateFloat, String chartName) {
    }
}
