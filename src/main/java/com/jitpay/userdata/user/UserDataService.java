package com.jitpay.userdata.user;

import com.jitpay.userdata.user.model.User;
import com.jitpay.userdata.user.model.UserDto;
import com.jitpay.userdata.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class UserDataService {

  private final UserRepository userRepository;


  public UserDataService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveOrUpdateUser(UserDto userDto) {
    User user = new User(userDto.getUserId(), userDto.getCreatedOn(),
        userDto.getEmail(), userDto.getFirstName(), userDto.getSecondName());

    return userRepository.save(user);
  }
}
