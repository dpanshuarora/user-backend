package com.jitpay.userdata.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jitpay.userdata.user.model.ErrorResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ApiExceptionHandlerTest {

  ApiExceptionHandler apiExceptionHandler;

  @BeforeEach
  public void setup() {
    apiExceptionHandler = new ApiExceptionHandler();
  }

  @Test
  public void shouldSendErrorResponseForClientErrors() {
    ResponseEntity responseEntity =
        apiExceptionHandler.handleInvalidTimestamp(new Exception("validation error"));

    ErrorResponseDto errorResponseDto = (ErrorResponseDto) responseEntity.getBody();
    assertEquals("Exception: validation error", errorResponseDto.getExceptionMessage());
    assertEquals(errorResponseDto.getFriendlyMessage(), "dateTimeStart cannot be greater then dateTimeEnd");
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_GATEWAY);
  }

}