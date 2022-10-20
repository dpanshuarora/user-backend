package com.jitpay.userdata.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jitpay.userdata.user.model.View.UserAndLatestLocation;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/** Represents the location entity as stored in location_t. It also stores the join relationship with
 * user_t and the filters defined using sql
 * @author deepanshu961@gmail.com
 * @version 1
 */

@Entity
@Getter
@Setter
@Table(name = "LOCATION_T", schema = "logistics")
@EnableAutoConfiguration
@FilterDef(name="createdOnFilter", parameters={@ParamDef( name="dateTimeStart", type="timestamp" ),
    @ParamDef( name="dateTimeEnd", type="timestamp" )},
    defaultCondition = "created_on between :dateTimeStart and :dateTimeEnd")
@FilterDef(name="latestLocationFilter",
    defaultCondition = "created_on = ( SELECT MAX(location_t.created_on) " +
    "FROM logistics.location_t " +
    "where USER_ID = location_t.USER_ID)")
public class Location implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String userId;

  private Double latitude;

  private Double longitude;

  @JsonView(View.UserAndLocationInRange.class)
  private Timestamp createdOn;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "userId",
      referencedColumnName = "userId",  insertable = false, updatable = false
  )
  private User user;

  @JsonProperty("location")
  @JsonView({View.UserAndLocationInRange.class, UserAndLatestLocation.class})
  public Map<String, Double> getLocationObject() {
    HashMap<String, Double> locations = new HashMap<>();
    locations.put("latitude", latitude);
    locations.put("longitude", longitude);
    return locations;
  }

}
