package com.java.tpi.cuongpham.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.tpi.cuongpham.constaints.ApiLabel;
import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.payload.response.BaseResponseBuilder;
import com.java.tpi.cuongpham.payload.response.CoinDeskResponse;
import com.java.tpi.cuongpham.service.iservice.ICoinDeskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinDeskService implements ICoinDeskService {
    private final String SOURCE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    /**
     * Call Coin Desk's API
     * @return raw response
     */
    @Override
    public BaseResponse getData() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(SOURCE_URL, String.class);
            ObjectMapper mapper = new ObjectMapper();
            CoinDeskResponse coinDeskResponse = mapper.readValue(response.getBody(), CoinDeskResponse.class);
            return BaseResponseBuilder.build(HttpStatusCode.OK.code, ApiLabel.GET_SUCCESS.text, coinDeskResponse);
        } catch (JsonProcessingException e) {
            return BaseResponseBuilder.build(HttpStatusCode.INTERNAL_SERVER_ERROR.code, ApiLabel.INTERNAL_SERVER_ERROR.text, e.getMessage());
        }
    }

    /**
     * Get Coin Desk's API for handle update exchange into out database
     * @return class CoinDeskResponse
     */
    @Override
    public CoinDeskResponse getDataSyncRate() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(SOURCE_URL, String.class);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.getBody(), CoinDeskResponse.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
