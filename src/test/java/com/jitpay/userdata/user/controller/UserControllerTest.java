package com.jitpay.userdata.user.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import com.jitpay.userdata.user.exceptions.StartDateAfterEndDateException;
import com.jitpay.userdata.user.repository.UserLocationRepository;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  UserLocationRepository userLocationRepository;

  @InjectMocks
  UserController userController;


  @Test
  void shouldGetUserDataWithLatestLocation() {
    userController.getUserDataWithLatestLocation("test-user-id");
    verify(userLocationRepository).findUserWithLatestLocation("test-user-id");
  }

  @Test
  void shouldGetUserLocationsBetweenTimeRange() throws StartDateAfterEndDateException {
    Timestamp startTimestamp = Timestamp.valueOf("2022-01-04 00:05:06");
    Timestamp endTimestamp = Timestamp.valueOf("2022-01-06 10:00:10");
    userController.getUserLocationsBetweenTimeRange("test-user-id", startTimestamp, endTimestamp);

    verify(userLocationRepository).getUserLocationsBetweenDateTimeRange("test-user-id",
        startTimestamp, endTimestamp);
  }

  @Test
  void shouldThrowExceptionWhenStartTimestampIsGreaterThanEndTimestamp() {
    Timestamp startTimestamp = Timestamp.valueOf("2022-01-04 00:05:06");
    Timestamp endTimestamp = Timestamp.valueOf("2022-01-01 00:00:00");

    assertThrows(StartDateAfterEndDateException.class,
        () -> userController.getUserLocationsBetweenTimeRange("test-user-id", startTimestamp, endTimestamp));

  }

}