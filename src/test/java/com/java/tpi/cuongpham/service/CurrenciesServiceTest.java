package com.java.tpi.cuongpham.service;

import com.java.tpi.cuongpham.entity.CurrenciesEntity;
import com.java.tpi.cuongpham.payload.request.CurrenciesInsertRequest;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.repository.CurrenciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CurrenciesServiceTest {
    @Mock
    CurrenciesRepository repository;

    @InjectMocks
    private  CurrenciesService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertSuccess() {
        BaseResponse response = new BaseResponse();
        CurrenciesInsertRequest request = new CurrenciesInsertRequest("EUR", "Euro", "&ksjadh;", "6.3", 6.3F, "ABC");
        when(repository.findByCcyCode(any())).thenReturn(Optional.empty());

        service.insert(request);

        verify(repository).save(any());
    }

    void testInsertFailWithExistCCY() {
        CurrenciesInsertRequest request = new CurrenciesInsertRequest("EUR", "Euro", "&ksjadh;", "6.3", 6.3F, "ABC");
        CurrenciesEntity entity = new CurrenciesEntity();
        when(repository.findByCcyCode(any())).thenReturn(Optional.of(entity));

        service.insert(request);

        verify(repository).save(any());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void insertCurrencyScheduler() {
    }

    @Test
    void updateCurrencyScheduler() {
    }

    @Test
    void checkExistCcyCode() {
    }
}