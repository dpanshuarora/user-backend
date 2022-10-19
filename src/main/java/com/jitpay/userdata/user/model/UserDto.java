package com.jitpay.userdata.user.model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** Represents the DTO used containing all the fields to store a record in USER_T
 * @author deepanshu961@gmail.com
 * @version 1
 */
@AllArgsConstructor
@Getter
public class UserDto {

  private String userId;

  private Timestamp createdOn;

  private String email;

  private String firstName;

  private String secondName;

}
