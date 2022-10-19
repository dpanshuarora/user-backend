package com.jitpay.userdata.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;

/** Represents the user entity as stored in user_t. It also stores the join relationship with location_t
 * @author deepanshu961@gmail.com
 * @version 1
 */
@Entity
@Table(name = "USER_T")
@NoArgsConstructor
@Getter
public class User implements Serializable {

  public User(String userId, Timestamp createdOn, String email, String firstName, String secondName) {
    this.userId = userId;
    this.createdOn = createdOn;
    this.email = email;
    this.firstName = firstName;
    this.secondName = secondName;
  }

  @Id
  @JsonView({View.UserAndLocationInRange.class, View.UserAndLatestLocation.class})
  private String userId;

  @JsonView(View.UserAndLatestLocation.class)
  private Timestamp createdOn;

  @JsonView(View.UserAndLatestLocation.class)
  private String email;

  @JsonView(View.UserAndLatestLocation.class)
  private String firstName;

  @JsonView(View.UserAndLatestLocation.class)
  private String secondName;

  @OneToMany(mappedBy = "user")
  @Filter(name = "createdOnFilter")
  @Filter(name = "latestLocationFilter")
  @JsonView(View.UserAndLocationInRange.class)
  private List<Location> locations;

  @JsonProperty("location")
  @JsonView(View.UserAndLatestLocation.class)
  private Map<String, Double> getLatestLocation() {
    Optional<List<Location>> locationsOptional = Optional.ofNullable(locations);

    if (locationsOptional.isPresent() && !locationsOptional.get().isEmpty()) {
      return locationsOptional.get().get(0).getLocationObject();
    } else {
      return new HashMap<>();
    }
  }

}
