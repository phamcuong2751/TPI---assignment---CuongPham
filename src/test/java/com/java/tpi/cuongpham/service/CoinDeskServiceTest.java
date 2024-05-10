package com.java.tpi.cuongpham.service;

import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.payload.response.CoinDeskResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CoinDeskServiceTest {
    @Mock
    private CoinDeskService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getData_success() {
        BaseResponse response = new BaseResponse();
        when(service.getData()).thenReturn(response);

        BaseResponse result = service.getData();

        assertEquals(response.getStatus_code(), result.getStatus_code());
    }

    @Test
    void test_getDataSyncRate_success() {
        CoinDeskResponse response = new CoinDeskResponse();
        when(service.getDataSyncRate()).thenReturn(response);

        CoinDeskResponse result = service.getDataSyncRate();

        assertEquals(response, result);
    }
}