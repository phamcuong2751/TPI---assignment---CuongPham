package com.java.tpi.cuongpham.mapper;

import com.java.tpi.cuongpham.client.dto.CurrencyObjectDTO;
import com.java.tpi.cuongpham.entity.CurrenciesEntity;
import com.java.tpi.cuongpham.payload.request.CurrenciesInsertRequest;
import com.java.tpi.cuongpham.payload.response.CoinDeskResponse;
import com.java.tpi.cuongpham.payload.response.CurrenciesResponse;
import com.java.tpi.cuongpham.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Date;

public class CurrenciesMapper {
    private static volatile CurrenciesMapper instance;

    public static CurrenciesMapper getInstance() {
        if (instance == null) {
            synchronized (CurrenciesMapper.class) {
                if (instance == null) {
                    instance = new CurrenciesMapper();
                }
            }
        }
        return instance;
    }

    public CurrenciesResponse toResponse(CurrenciesEntity entity) {
        CurrenciesResponse response = new CurrenciesResponse();
        response.setCode(entity.getCcyCode());
        response.setName(entity.getCcyName());
        response.setSymbol(entity.getCcySymbol());
        response.setRate(entity.getRate());
        response.setRate_float(entity.getRateFloat());
        response.setChartName(entity.getChartName());
        response.setCreateAt(DateUtils.convertLocalTime(entity.getCreateAt()));
        response.setUpdateAt(DateUtils.convertLocalTime(entity.getUpdateAt()));
        return response;
    }

    public CurrenciesEntity toEntity(CurrenciesInsertRequest request) {
        CurrenciesEntity entity = new CurrenciesEntity();
        entity.setCcyCode(request.getCode());
        entity.setCcyName(request.getName());
        entity.setCcySymbol(request.getSymbol());
        entity.setRate(request.getRate());
        entity.setRateFloat(request.getRateFloat());
        entity.setChartName(request.getChartName());
        entity.setCreateAt(LocalDateTime.now());
        entity.setUpdateAt(LocalDateTime.now());
        return entity;
    }

    public CurrenciesEntity toEntity(CurrencyObjectDTO currency) {
        CurrenciesEntity entity = new CurrenciesEntity();
        entity.setCcyCode(currency.getCode());
        entity.setCcyName(currency.getDescription());
        entity.setCcySymbol(currency.getSymbol());
        entity.setRate(currency.getRate());
        entity.setRateFloat(currency.getRate_float());
        return entity;
    }
}
