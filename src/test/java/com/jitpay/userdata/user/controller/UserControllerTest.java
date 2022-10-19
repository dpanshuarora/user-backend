package com.jitpay.userdata.user.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jitpay.userdata.user.UserDataService;
import com.jitpay.userdata.user.exceptions.StartDateAfterEndDateException;
import com.jitpay.userdata.user.model.User;
import com.jitpay.userdata.user.model.UserDto;
import com.jitpay.userdata.user.repository.UserLocationRepository;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  UserLocationRepository userLocationRepository;

  @Mock
  UserDataService userDataService;

  @Mock
  HttpServletRequest request;

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

  @Test
  void shouldPersistUser() {
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    UserDto userDto = getTestUserDto();
    when(userDataService.saveOrUpdateUser(userDto)).thenReturn(new User(userDto.getUserId(), userDto.getCreatedOn(),
        userDto.getEmail(), userDto.getFirstName(), userDto.getSecondName()));
    userController.saveOrUpdateUser(userDto);

    verify(userDataService).saveOrUpdateUser(userDto);

  }

  private UserDto getTestUserDto() {
    return new UserDto("id", Timestamp.valueOf("2022-01-06 10:00:10"), "test@email.com", "FN", "SN");
  }

}