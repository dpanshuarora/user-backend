package com.jitpay.userdata.user.controller;

import com.jitpay.userdata.user.exceptions.StartDateAfterEndDateException;
import com.jitpay.userdata.user.model.ErrorResponseDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(StartDateAfterEndDateException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleInvalidTimestamp(Exception ex) {
    return createPayload(ex, "dateTimeStart cannot be greater then dateTimeEnd",
        HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Object> createPayload(Exception ex,
      String friendlyMessage,
      HttpStatus status) {
    ErrorResponseDto apiError = new ErrorResponseDto(
        ExceptionUtils.getMessage(ex), friendlyMessage, ExceptionUtils.getStackTrace(ex));

    return new ResponseEntity<>(
        apiError, new HttpHeaders(), status);
  }
}



