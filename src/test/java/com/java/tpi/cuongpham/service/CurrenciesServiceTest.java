package com.java.tpi.cuongpham.service;

import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.entity.CurrenciesEntity;
import com.java.tpi.cuongpham.payload.request.CurrenciesInsertRequest;
import com.java.tpi.cuongpham.payload.request.CurrenciesUpdateRequest;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.repository.CurrenciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrenciesServiceTest {

    @Mock
    private CurrenciesRepository currenciesRepository;

    @InjectMocks
    private CurrenciesService currenciesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertCurrency_NotExists() {
        CurrenciesInsertRequest request = new CurrenciesInsertRequest("GBP", "Pound Sterling", "£", "0.8", 0.8F, "forex");
        when(currenciesRepository.findByCcyCode(request.getCode())).thenReturn(Optional.empty());
        when(currenciesRepository.save(any(CurrenciesEntity.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        BaseResponse response = currenciesService.insert(request);

        assertEquals(HttpStatusCode.OK.code, response.getStatus_code());
        verify(currenciesRepository).save(any(CurrenciesEntity.class));
    }

    @Test
    public void testInsertCurrency_Exists() {
        CurrenciesInsertRequest request = new CurrenciesInsertRequest("USD", "Dollar", "$", "1.0", 1.0F, "forex");
        when(currenciesRepository.findByCcyCode(request.getCode())).thenReturn(Optional.of(new CurrenciesEntity()));

        BaseResponse response = currenciesService.insert(request);

        assertEquals(HttpStatusCode.BAD_REQUEST.code, response.getStatus_code());
        verify(currenciesRepository, never()).save(any(CurrenciesEntity.class));
    }

    @Test
    public void testUpdateCurrency_Exists() {
        CurrenciesUpdateRequest request = new CurrenciesUpdateRequest("USD", "Dollar", "$324", "1.2", 1.2F, "forex");
        CurrenciesEntity existingEntity = new CurrenciesEntity("USD", "Old Dollar", "$ewr", "1.0", 1.0F, "old forex");

        when(currenciesRepository.findByCcyCode(request.getCode())).thenReturn(Optional.of(existingEntity));
        when(currenciesRepository.save(any(CurrenciesEntity.class))).thenReturn(existingEntity);

        BaseResponse response = currenciesService.update(request);

        assertEquals(HttpStatusCode.OK.code, response.getStatus_code());
        verify(currenciesRepository).save(existingEntity);
        assertNotNull(response);
    }

    @Test
    public void testUpdateCurrency_NotExists() {
        CurrenciesUpdateRequest request = new CurrenciesUpdateRequest("EUR", "Euro", "€", "1.1", 1.1F, "forex");
        when(currenciesRepository.findByCcyCode(request.getCode())).thenReturn(Optional.empty());

        BaseResponse response = currenciesService.update(request);

        assertEquals(HttpStatusCode.BAD_REQUEST.code, response.getStatus_code());
        verify(currenciesRepository, never()).save(any(CurrenciesEntity.class));
    }

    @Test
    public void testDeleteCurrency_Exists() {
        String ccyCode = "USD";
        when(currenciesRepository.findByCcyCode(ccyCode)).thenReturn(Optional.of(new CurrenciesEntity()));
        doNothing().when(currenciesRepository).deleteByCcyCode(ccyCode);

        BaseResponse response = currenciesService.delete(ccyCode);

        assertEquals(HttpStatusCode.OK.code, response.getStatus_code());
        verify(currenciesRepository).deleteByCcyCode(ccyCode);
    }

    @Test
    public void testDeleteCurrency_NotExists() {
        String ccyCode = "EUR";
        when(currenciesRepository.findByCcyCode(ccyCode)).thenReturn(Optional.empty());

        BaseResponse response = currenciesService.delete(ccyCode);

        assertEquals(HttpStatusCode.BAD_REQUEST.code, response.getStatus_code());
        verify(currenciesRepository, never()).deleteByCcyCode(ccyCode);
    }

}