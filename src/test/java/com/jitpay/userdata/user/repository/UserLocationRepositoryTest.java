package com.jitpay.userdata.user.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserLocationRepositoryTest {

  @Mock
  private EntityManager entityManager;

  @Mock
  private Session session;

  @Mock
  private Filter filter;

  @Mock
  private TypedQuery typedQuery;

  @InjectMocks
  private UserLocationRepository userLocationRepository;

  @BeforeEach
  public void setUpSession() {
    when(entityManager.unwrap(Session.class)).thenReturn(session);
  }

  @Test
  void setFiltersAndParamsForGetUserLocationsBetweenDateTimeRangeQuery() {
    when(session.enableFilter(anyString())).thenReturn(filter);
    when(filter.setParameter(anyString(), any(Timestamp.class))).thenReturn(filter);
    when(entityManager.createQuery(any(), any())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(new ArrayList());

    userLocationRepository.getUserLocationsBetweenDateTimeRange("user-id",
        Timestamp.valueOf("2022-01-06 10:00:10"), Timestamp.valueOf("2022-01-07 00:00:10"));

    verify(session).enableFilter("createdOnFilter");
    verify(typedQuery).setParameter("userId", "user-id");
    verify(filter).setParameter("dateTimeStart", Timestamp.valueOf("2022-01-06 10:00:10"));
    verify(filter).setParameter("dateTimeEnd", Timestamp.valueOf("2022-01-07 00:00:10"));
  }

}