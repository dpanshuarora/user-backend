package com.jitpay.userdata.user.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jitpay.userdata.user.model.User;
import com.jitpay.userdata.user.model.View;
import com.jitpay.userdata.user.exceptions.StartDateAfterEndDateException;
import com.jitpay.userdata.user.repository.UserLocationRepository;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-management")
public class UserController {

  @Autowired
  private UserLocationRepository userLocationRepository;

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

}
