package com.java.tpi.cuongpham.controller;

import com.java.tpi.cuongpham.constaints.ApiLabel;
import com.java.tpi.cuongpham.constaints.HttpStatusCode;
import com.java.tpi.cuongpham.payload.response.BaseResponse;
import com.java.tpi.cuongpham.service.CoinDeskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CoinDeskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private CoinDeskService coinDeskService;
    @InjectMocks
    private CoinDeskController controller;

    @Test
    public void getCurrencies_ShouldReturnCurrencies_WhenServiceReturnsData() throws Exception {
        BaseResponse mockResponse = new BaseResponse();
        mockResponse.setData(any());
        mockResponse.setMessage(ApiLabel.GET_SUCCESS.text);
        mockResponse.setStatus_code(200);

        when(coinDeskService.getData()).thenReturn(mockResponse);

        ResponseEntity<BaseResponse> response = controller.getCurrencies();

        assertNotNull(response.getBody());
        assertEquals(200, response.getBody().getStatus_code());
        assertEquals(ApiLabel.GET_SUCCESS.text, response.getBody().getMessage());
    }


    @Test
    void getCurrencies_ShouldReturnInternalServerError_WhenServiceThrowsException() throws Exception {
        when(coinDeskService.getData()).thenThrow(new RuntimeException("Internal server error"));

        ResponseEntity<BaseResponse> response = controller.getCurrencies();

        assertNotNull(response.getBody());
        assertEquals(HttpStatusCode.INTERNAL_SERVER_ERROR.code, response.getBody().getStatus_code());
        assertTrue(response.getBody().getMessage().contains("Internal server error"));
    }
}