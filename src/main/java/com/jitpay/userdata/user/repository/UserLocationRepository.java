package com.jitpay.userdata.user.repository;

import com.jitpay.userdata.user.model.User;
import java.sql.Timestamp;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserLocationRepository {

  private final EntityManager entityManager;

  public UserLocationRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public User getUserLocationsBetweenDateTimeRange(String userId, Timestamp start,
      Timestamp end) {

    Session session = entityManager.unwrap(Session.class);
    session.enableFilter("createdOnFilter")
        .setParameter("dateTimeStart", start)
        .setParameter("dateTimeEnd", end);

    TypedQuery<User> query = entityManager.createQuery(
        "SELECT user FROM User user where user.userId = :userId ", User.class);

    return query
        .setParameter("userId", userId)
        .getResultList()
        .stream().findFirst().orElse(null);

  }

}
