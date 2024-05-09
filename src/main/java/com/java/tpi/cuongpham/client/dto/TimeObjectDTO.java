package com.java.tpi.cuongpham.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeObjectDTO {
    private String updated;
    @JsonProperty("updatedISO")
    private String updatedIso;
    private String updateduk;
}
