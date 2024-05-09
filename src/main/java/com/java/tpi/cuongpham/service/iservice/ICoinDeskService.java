package com.java.tpi.cuongpham.service.iservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.payload.response.CoinDeskResponse;

public interface ICoinDeskService {
    BaseResponse getData();
    CoinDeskResponse getDataSyncRate();
}
