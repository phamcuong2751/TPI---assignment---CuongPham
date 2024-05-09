package com.java.tpi.cuongpham.service;

import com.java.tpi.cuongpham.client.dto.CurrencyObjectDTO;
import com.java.tpi.cuongpham.payload.response.CoinDeskResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SyncExChangeRateService {
    private CoinDeskService coinDeskService;
    private CurrenciesService currenciesService;

    public void execution() {
        System.out.println("Scheduler Sync Exchange Rates Start >>>>");
        CoinDeskResponse response = coinDeskService.getDataSyncRate();
        response.getBpi().forEach((key, value) -> {
            System.out.println("Exist CCY: " + key + " " + currenciesService.checkExistCcyCode(value.getCode()));
            if (!currenciesService.checkExistCcyCode(value.getCode())) {
                currenciesService.insertCurrencyScheduler(value, response.getChartName());
            } else {
                currenciesService.updateCurrencyScheduler(value, response.getChartName());
            }
        });
        System.out.println("Scheduler Sync Exchange Rates END >>>>");
    }
}
