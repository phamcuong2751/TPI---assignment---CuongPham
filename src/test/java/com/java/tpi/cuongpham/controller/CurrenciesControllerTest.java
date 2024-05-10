package com.java.tpi.cuongpham.controller;

import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.payload.request.CurrenciesInsertRequest;
import com.java.tpi.cuongpham.payload.request.CurrenciesUpdateRequest;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.service.CurrenciesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrenciesControllerTest {
    @Mock
    private CurrenciesService currenciesService;

    @InjectMocks
    private CurrenciesController currenciesController;

    @Test
    public void testGetCurrencies_Success() throws Exception {
        BaseResponse mockResponse = new BaseResponse();
        when(currenciesService.getCurrencies("USD", "asc")).thenReturn(mockResponse);

        ResponseEntity<BaseResponse> response = currenciesController.getCurrencies("USD", "asc");
        verify(currenciesService).getCurrencies("USD", "asc");
    }

    @Test
    public void testGetCurrencies_Failure() throws Exception {
        when(currenciesService.getCurrencies("USD", "asc")).thenThrow(new RuntimeException("Service failure"));

        ResponseEntity<BaseResponse> response = currenciesController.getCurrencies("USD", "asc");

        assertNotNull(response.getBody());
        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());
    }

    @Test
    public void testInsertCurrencies_Success() throws Exception {
        CurrenciesInsertRequest request = new CurrenciesInsertRequest();
        BaseResponse mockResponse = new BaseResponse();
        when(currenciesService.insert(request)).thenReturn(mockResponse);

        ResponseEntity<BaseResponse> response = currenciesController.insertCurrencies(request);

        assertEquals(HttpStatusCode.OK.code, response.getBody().getStatus_code());
    }

    @Test
    public void testInsertCurrencies_Failure() throws Exception {
        CurrenciesInsertRequest request = new CurrenciesInsertRequest();
        when(currenciesService.insert(request)).thenThrow(new RuntimeException("Service failure"));

        ResponseEntity<BaseResponse> response = currenciesController.insertCurrencies(request);

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());  // Note this seems incorrect in your controller should likely be INTERNAL_SERVER_ERROR
        assertNotNull(response.getBody());
        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());
    }

    @Test
    public void testUpdateCurrencies_Success() throws Exception {
        CurrenciesUpdateRequest request = new CurrenciesUpdateRequest("USD", "Dollar", "$", "1.2", 1.2F, "forex");  // Assume CurrenciesUpdateRequest is properly defined
        BaseResponse mockResponse = new BaseResponse();
        when(currenciesService.update(request)).thenReturn(mockResponse);

        ResponseEntity<BaseResponse> response = currenciesController.updateCurrencies(request);

        assertEquals(HttpStatusCode.OK.code, response.getBody().getStatus_code());
        verify(currenciesService).update(request);
    }

    @Test
    public void testUpdateCurrencies_Failure() throws Exception {
        CurrenciesUpdateRequest request = new CurrenciesUpdateRequest("USD", "Dollar", "$", "1.2", 1.2F, "forex");
        when(currenciesService.update(request)).thenThrow(new RuntimeException("Service failure"));

        ResponseEntity<BaseResponse> response = currenciesController.updateCurrencies(request);

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());  // Note: This is consistent with your controller, but should likely be INTERNAL_SERVER_ERROR
        assertNotNull(response.getBody());
        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());
    }

    @Test
    public void testDeleteCurrencies_Success() throws Exception {
        String currency = "USD";
        BaseResponse mockResponse = new BaseResponse();
        when(currenciesService.delete(currency)).thenReturn(mockResponse);

        ResponseEntity<BaseResponse> response = currenciesController.deleteCurrencies(currency);

        assertEquals(HttpStatusCode.OK.code, response.getBody().getStatus_code());
        verify(currenciesService).delete(currency);
    }

    @Test
    public void testDeleteCurrencies_Failure() throws Exception {
        String currency = "USD";
        when(currenciesService.delete(currency)).thenThrow(new RuntimeException("Service failure"));

        ResponseEntity<BaseResponse> response = currenciesController.deleteCurrencies(currency);

        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());  // Note: This should likely be INTERNAL_SERVER_ERROR
        assertNotNull(response.getBody());
        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());
    }

}