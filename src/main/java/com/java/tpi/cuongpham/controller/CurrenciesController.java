package com.java.tpi.cuongpham.controller;

import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.payload.request.CurrenciesInsertRequest;
import com.java.tpi.cuongpham.payload.request.CurrenciesUpdateRequest;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.payload.response.BaseResponseBuilder;
import com.java.tpi.cuongpham.service.CurrenciesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/currency")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CurrenciesController {
    private final CurrenciesService currenciesService;
    /**
     * Get all list currencies
     * @return List Currency
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCurrencies(@RequestParam(name = "currency") String currency,
                                                      @RequestParam(name = "direction") String direction) {
        try {
            BaseResponse response = currenciesService.getCurrencies(currency, direction);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponseBuilder.build(HttpStatusCode.INTERNAL_SERVER_ERROR.code, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Insert new currency
     * @return List Currency
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> insertCurrencies(@RequestBody @Valid CurrenciesInsertRequest request) {
        try {
            BaseResponse response = currenciesService.insert(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponseBuilder.build(HttpStatusCode.INTERNAL_SERVER_ERROR.code, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Update currency
     * @return List Currency
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<BaseResponse> updateCurrencies(@RequestBody @Valid CurrenciesUpdateRequest request) {
        try {
            BaseResponse response = currenciesService.update(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponseBuilder.build(HttpStatusCode.INTERNAL_SERVER_ERROR.code, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Delete currency
     * @return List Currency
     */
    @RequestMapping(value = "/{currency}", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResponse> deleteCurrencies(@PathVariable("currency") String currency) {
        try {
            BaseResponse response = currenciesService.delete(currency);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponseBuilder.build(HttpStatusCode.INTERNAL_SERVER_ERROR.code, e.getMessage()), HttpStatus.OK);
        }
    }
}
