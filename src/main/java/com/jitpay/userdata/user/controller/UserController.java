package com.jitpay.userdata.user.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jitpay.userdata.user.UserDataService;
import com.jitpay.userdata.user.exceptions.StartDateAfterEndDateException;
import com.jitpay.userdata.user.model.User;
import com.jitpay.userdata.user.model.UserDto;
import com.jitpay.userdata.user.model.View;
import com.jitpay.userdata.user.repository.UserLocationRepository;
import java.net.URI;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/user-management")
public class UserController {

  @Autowired
  private UserLocationRepository userLocationRepository;

  @Autowired
  private UserDataService userDataService;

  @GetMapping("/user/latest-location")
  @JsonView(View.UserAndLatestLocation.class)
  public @ResponseBody
  User getUserDataWithLatestLocation(@RequestParam String userId) {

    return userLocationRepository.findUserWithLatestLocation(userId);

  }

  @GetMapping("/user/locations")
  @JsonView(View.UserAndLocationInRange.class)
  public @ResponseBody
  User getUserLocationsBetweenTimeRange(@RequestParam String userId,
      @RequestParam Timestamp dateTimeStart, @RequestParam Timestamp dateTimeEnd)
      throws StartDateAfterEndDateException {

    if (dateTimeStart.after(dateTimeEnd)) {
      throw new StartDateAfterEndDateException();
    }

    return userLocationRepository.getUserLocationsBetweenDateTimeRange(userId, dateTimeStart, dateTimeEnd);

  }

  @PostMapping("/user")
  @JsonView(View.UserAndLocationInRange.class)
  public @ResponseBody
  ResponseEntity<Object> saveOrUpdateUser(@RequestBody UserDto userDto) {

    User savedUser = userDataService.saveOrUpdateUser(userDto);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/latest-location")
        .queryParam("userId", userDto.getUserId())
        .buildAndExpand(savedUser.getUserId()).toUri();

    return ResponseEntity.created(location).build();

  }

}
