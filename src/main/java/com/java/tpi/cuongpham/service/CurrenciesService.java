package com.java.tpi.cuongpham.service;

import com.java.tpi.cuongpham.client.dto.CurrencyObjectDTO;
import com.java.tpi.cuongpham.constaints.ApiLabel;
import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.entity.CurrenciesEntity;
import com.java.tpi.cuongpham.mapper.CurrenciesMapper;
import com.java.tpi.cuongpham.payload.request.CurrenciesInsertRequest;
import com.java.tpi.cuongpham.payload.request.CurrenciesUpdateRequest;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.payload.response.BaseResponseBuilder;
import com.java.tpi.cuongpham.payload.response.CurrenciesResponse;
import com.java.tpi.cuongpham.repository.CurrenciesRepository;
import com.java.tpi.cuongpham.service.iservice.ICurrenciesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrenciesService implements ICurrenciesService {
    private final CurrenciesRepository currenciesRepository;

    @Override
    public BaseResponse getCurrencies(String ccyCode, String direction) {
        List<CurrenciesEntity> currencies;
        if (ccyCode.isEmpty()) {
            currencies = currenciesRepository.findAll(getSort(direction));
        } else {
            currencies = currenciesRepository.findAllByCcyCodeLike(ccyCode);
        }

        List<CurrenciesResponse> responses = new ArrayList<>();
        if (!currencies.isEmpty()) {
            for (CurrenciesEntity currency : currencies) {
                CurrenciesResponse response = CurrenciesMapper.getInstance().toResponse(currency);
                responses.add(response);
            }
            return BaseResponseBuilder.build(HttpStatusCode.OK.code, ApiLabel.GET_SUCCESS.text, responses);
        }
        return BaseResponseBuilder.build(HttpStatusCode.NOT_FOUND.code, ApiLabel.NOT_FOUND.text);
    }

    @Override
    @Transactional
    public BaseResponse insert(CurrenciesInsertRequest request) {
        boolean existCcyCode = currenciesRepository.findByCcyCode(request.getCode()).map(entity -> Boolean.TRUE).orElse(Boolean.FALSE);
        if (!existCcyCode) {
            currenciesRepository.save(CurrenciesMapper.getInstance().toEntity(request));
            return BaseResponseBuilder.build(HttpStatusCode.OK.code, ApiLabel.INSERT_SUCCESS.text + " " + request.getCode());
        }
        return BaseResponseBuilder.build(HttpStatusCode.BAD_REQUEST.code, ApiLabel.BAD_REQUEST.text);
    }

    @Override
    @Transactional
    public BaseResponse update(CurrenciesUpdateRequest request) {
        Optional<CurrenciesEntity> optionalCurrencies = currenciesRepository.findByCcyCode(request.getCode());
        if (optionalCurrencies.isPresent()) {
            CurrenciesEntity entity = getCurrenciesEntity(request, optionalCurrencies);
            currenciesRepository.save(entity);
            return BaseResponseBuilder.build(HttpStatusCode.OK.code, ApiLabel.UPDATE_SUCCESS.text + " " + request.getCode());
        }
        return BaseResponseBuilder.build(HttpStatusCode.BAD_REQUEST.code, ApiLabel.BAD_REQUEST.text);
    }

    @Override
    @Transactional
    public BaseResponse delete(String ccyCode) {
        boolean existCcyCode = checkExistCcyCode(ccyCode);
        if (existCcyCode) {
            currenciesRepository.deleteByCcyCode(ccyCode);
            return BaseResponseBuilder.build(HttpStatusCode.OK.code, ApiLabel.DELETE_SUCCESS.text + " " + ccyCode);
        }
        return BaseResponseBuilder.build(HttpStatusCode.BAD_REQUEST.code, ApiLabel.BAD_REQUEST.text);
    }

    @Override
    public void insertCurrencyScheduler(CurrencyObjectDTO currency, String chartName) {
        CurrenciesEntity entity = CurrenciesMapper.getInstance().toEntity(currency);
        entity.setChartName(chartName);
        entity.setCreateAt(LocalDateTime.now());
        entity.setUpdateAt(LocalDateTime.now());
        currenciesRepository.save(entity);
    }

    @Override
    public void updateCurrencyScheduler(CurrencyObjectDTO currency, String chartName) {
        Optional<CurrenciesEntity> optionalEntity = currenciesRepository.findByCcyCode(currency.getCode());
        if (optionalEntity.isPresent()) {
            CurrenciesEntity entity = optionalEntity.get();
            if (!currency.getCode().equals(entity.getCcyCode())){
                entity.setCcyCode(currency.getCode());
            }
            if (!currency.getDescription().equals(entity.getCcyName())){
                entity.setCcyName(currency.getDescription());
            }
            if (!currency.getSymbol().equals(entity.getCcySymbol())){
                entity.setCcySymbol(currency.getSymbol());
            }
            if (!currency.getRate().equals(entity.getRate())){
                entity.setRate(currency.getRate());
            }
            if (currency.getRate_float() != entity.getRateFloat()){
                entity.setRateFloat(currency.getRate_float());
            }
            if (!chartName.equals(entity.getChartName())){
                entity.setChartName(chartName);
            }
            entity.setUpdateAt(LocalDateTime.now());
            currenciesRepository.save(entity);
        }
    }

    @Override
    public Boolean checkExistCcyCode(String ccyCode) {
        return currenciesRepository.findByCcyCode(ccyCode).map(entity -> Boolean.TRUE).orElse(Boolean.FALSE);
    }

    private static CurrenciesEntity getCurrenciesEntity(CurrenciesUpdateRequest request, Optional<CurrenciesEntity> optionalCurrencies) {
        CurrenciesEntity entity = optionalCurrencies.get();
        if (!request.getName().equals(entity.getCcyName())) {
            entity.setCcyName(request.getName());
        }
        if (!request.getSymbol().equals(entity.getCcySymbol())) {
            entity.setCcySymbol(request.getSymbol());
        }
        if (!request.getRate().equals(entity.getRate())) {
            entity.setRate(request.getRate());
        }
        if (request.getRateFloat() != entity.getRateFloat()) {
            entity.setRateFloat(request.getRateFloat());
        }
        if (request.getChartName().equals(entity.getChartName())) {
            entity.setChartName(request.getChartName());
        }
        return entity;
    }

    private Sort getSort(String direction) {
        if (direction.equals("ASC")) {
            return Sort.by(Sort.Direction.ASC, "ccyCode");
        }
        return Sort.by(Sort.Direction.DESC, "ccyCode");
    }


}
