package com.java.tpi.cuongpham.service.iservice;

import com.java.tpi.cuongpham.client.dto.CurrencyObjectDTO;
import com.java.tpi.cuongpham.payload.request.CurrenciesInsertRequest;
import com.java.tpi.cuongpham.payload.request.CurrenciesUpdateRequest;
import com.java.tpi.cuongpham.payload.response.BaseResponse;

public interface ICurrenciesService {
    BaseResponse getCurrencies(String currency, String direction);

    BaseResponse insert(CurrenciesInsertRequest request);

    BaseResponse update(CurrenciesUpdateRequest request);

    BaseResponse delete(String ccyCode);

    void insertCurrencyScheduler(CurrencyObjectDTO currencyObject, String chartName);

    void updateCurrencyScheduler(CurrencyObjectDTO currencyObject, String chartName);

    Boolean checkExistCcyCode(String ccyCode);
}
