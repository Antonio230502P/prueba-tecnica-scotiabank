package com.scotiabank.technical_test.controller;

import com.scotiabank.technical_test.dto.CustomErrorResponse;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomGlobalExceptionControllerTest {

    @InjectMocks
    private CustomGlobalExceptionController customGlobalExceptionController;

    private MockHttpServletRequest mockRequest;
    private ServletWebRequest servletWebRequest;

    @BeforeEach
    public void setUp() {
        customGlobalExceptionController = new CustomGlobalExceptionController();
        mockRequest = new MockHttpServletRequest();
        servletWebRequest = new ServletWebRequest(mockRequest);
    }

    @Test
    public void testHandleFeignStatusException_NotFound() {
        FeignException feignException = mock(FeignException.class);
        when(feignException.status()).thenReturn(404);

        mockRequest.setRequestURI("/api/pet/1");
        ResponseEntity<CustomErrorResponse> response = customGlobalExceptionController.handleFeignStatusException(feignException, servletWebRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No existe ninguna mascota con ese id", response.getBody().getMessage());
        assertEquals("/api/pet/1", response.getBody().getPath());
    }

    @Test
    public void testHandleFeignStatusException_OtherStatus() {
        FeignException feignException = mock(FeignException.class);
        when(feignException.status()).thenReturn(500);
        when(feignException.contentUTF8()).thenReturn("Internal Server Error");

        mockRequest.setRequestURI("/api/pet/1");
        ResponseEntity<CustomErrorResponse> response = customGlobalExceptionController.handleFeignStatusException(feignException, servletWebRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", response.getBody().getMessage());
        assertEquals("/api/pet/1", response.getBody().getPath());
    }
}
