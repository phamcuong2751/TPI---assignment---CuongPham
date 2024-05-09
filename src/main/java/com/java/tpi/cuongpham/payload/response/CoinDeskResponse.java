package com.java.tpi.cuongpham.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.tpi.cuongpham.client.dto.CurrencyObjectDTO;
import com.java.tpi.cuongpham.client.dto.TimeObjectDTO;
import lombok.Data;

import java.util.Map;

@Data
public class CoinDeskResponse {
    private TimeObjectDTO time;
    private String disclaimer;
    private String chartName;
    @JsonProperty("bpi")
    private Map<String, CurrencyObjectDTO> bpi;
}
