package com.jitpay.userdata.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import com.jitpay.userdata.user.model.User;
import com.jitpay.userdata.user.model.UserDto;
import com.jitpay.userdata.user.repository.UserRepository;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDataServiceTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserDataService userDataService;

  @Test
  void saveOrUpdateUser() {
    UserDto userDto = getTestUserDto();
    userDataService.saveOrUpdateUser(userDto);

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    verify(userRepository).save(userArgumentCaptor.capture());

    User user = userArgumentCaptor.getValue();
    assertEquals("id", user.getUserId());
    assertEquals(Timestamp.valueOf("2022-01-06 10:00:10"), user.getCreatedOn());
    assertEquals("test@email.com", user.getEmail());
    assertEquals("FN", user.getFirstName());
    assertEquals("SN", user.getSecondName());
  }

  private UserDto getTestUserDto() {
    return new UserDto("id", Timestamp.valueOf("2022-01-06 10:00:10"), "test@email.com", "FN", "SN");
  }
}