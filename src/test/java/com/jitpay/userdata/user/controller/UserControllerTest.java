package com.jitpay.userdata.user.controller;

import static org.mockito.Mockito.verify;

import com.jitpay.userdata.user.repository.UserLocationRepository;
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

}