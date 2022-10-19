package com.jitpay.userdata.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

  private String exceptionMessage;
  private String friendlyMessage;
  private String stacktrace;
}