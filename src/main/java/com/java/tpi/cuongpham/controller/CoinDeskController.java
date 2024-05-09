package com.java.tpi.cuongpham.controller;

import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.payload.response.BaseResponseBuilder;
import com.java.tpi.cuongpham.service.CoinDeskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/coin-desk")
@AllArgsConstructor
public class CoinDeskController {
    private final CoinDeskService coinDeskService;

    /**
     * Call Coin Desk's API
     * @return full Coin Desk's API
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCurrencies() {
        try {
            BaseResponse response = coinDeskService.getData();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponseBuilder.build(HttpStatusCode.INTERNAL_SERVER_ERROR.code, e.getMessage()), HttpStatus.OK);
        }
    }
}
