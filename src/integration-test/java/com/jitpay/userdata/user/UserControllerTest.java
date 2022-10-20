package com.jitpay.userdata.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitpay.userdata.user.model.ErrorResponseDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = {"integration-test"})
public class UserControllerTest {

  @LocalServerPort
  private int randomServerPort;

  TestRestTemplate restTemplate = new TestRestTemplate();

  @BeforeEach
  void setUp() {

  }

  @Test
  void shouldGetUserWithLatestLocation()
      throws URISyntaxException {
    String baseUrl =
        "http://localhost:" + randomServerPort + "/user-management/user/latest-location";

    String urlWithParams = baseUrl + "?userId=2e3b11b0-07a4-4873-8de5-d2ae2eab26b2";
    ResponseEntity<String> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, String.class);
    String responseBody = response.getBody();

    assertEquals(
        "{\"userId\":\"2e3b11b0-07a4-4873-8de5-d2ae2eab26b2\",\"createdOn\":\"2022-06-09T22:35:06.000+00:00\""
            + ",\"email\":\"abc@gmail.com\",\"firstName\":\"John\",\"secondName\":\"Doe\",\"location\":{\"latitude\":50.25700491800493"
            + ",\"longitude\":15.528306568625961}}", responseBody);

  }

  @Test
  void shouldGetEmptyLocationWhenNoLocationExistsForUser()
      throws URISyntaxException {
    String baseUrl =
        "http://localhost:" + randomServerPort + "/user-management/user/latest-location";

    String urlWithParams = baseUrl + "?userId=buuyby3-4d3d-dff5d-efff3f-3biufbhiu3if";
    ResponseEntity<String> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, String.class);
    String responseBody = response.getBody();

    assertEquals("{\"userId\":\"buuyby3-4d3d-dff5d-efff3f-3biufbhiu3if\",\"createdOn\":"
        + "\"2022-06-09T22:35:06.000+00:00\",\"email\":\"abc@gmail.com\",\"firstName\":\"Pam\","
        + "\"secondName\":\"Brown\",\"location\":{}}", responseBody);

  }

  @Test
  void shouldGetEmptyBodyWhenUserIdDoesntExist()
      throws URISyntaxException {
    String baseUrl =
        "http://localhost:" + randomServerPort + "/user-management/user/latest-location";

    String urlWithParams = baseUrl + "?userId=invalid-id";
    ResponseEntity<String> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, String.class);
    String responseBody = response.getBody();

    assertNull(responseBody);

  }

  @Test
  void shouldGetUserWithAllLocationsWithinDatetimeRange()
      throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/user-management/user/locations";

    String urlWithParams =
        baseUrl + "?userId=8b8eh3j2-dhb3-8473-8ossh-d83n4h4hh4j4j4&dateTimeStart="
            + "2022-01-04%2000:05:06&dateTimeEnd=2022-01-06%2022:05:06";
    ResponseEntity<String> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, String.class);
    String responseBody = response.getBody();

    assertEquals("{\"userId\":\"8b8eh3j2-dhb3-8473-8ossh-d83n4h4hh4j4j4\",\"locations\":"
            + "[{\"createdOn\":\"2022-01-05T22:35:06.000+00:00\",\"location\":{\"latitude\":93.25700491800492,\"longitude\":9.528306568625961}},"
            + "{\"createdOn\":\"2022-01-03T23:35:06.000+00:00\",\"location\":{\"latitude\":94.25700491800492,\"longitude\":9.528306568625961}}]}",
        responseBody);

  }


  @Test
  void shouldGetUserWithLocationsFieldEmptyWhenLocationsNotPresentForTimeRange()
      throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/user-management/user/locations";

    String urlWithParams =
        baseUrl + "?userId=8b8eh3j2-dhb3-8473-8ossh-d83n4h4hh4j4j4&dateTimeStart="
            + "2022-01-09%2000:05:09&dateTimeEnd=2022-01-09%2022:05:06";
    ResponseEntity<String> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, String.class);
    String responseBody = response.getBody();

    assertEquals("{\"userId\":\"8b8eh3j2-dhb3-8473-8ossh-d83n4h4hh4j4j4\",\"locations\":[]}",
        responseBody);

  }


  @Test
  void shouldGetUserWithLocationsFieldEmptyWhenLocationsNotPresentForUserId()
      throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/user-management/user/locations";

    String urlWithParams =
        baseUrl + "?userId=buuyby3-4d3d-dff5d-efff3f-3biufbhiu3if&dateTimeStart="
            + "2022-01-09%2000:05:09&dateTimeEnd=2022-01-09%2022:05:06";
    ResponseEntity<String> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, String.class);
    String responseBody = response.getBody();

    assertEquals("{\"userId\":\"buuyby3-4d3d-dff5d-efff3f-3biufbhiu3if\",\"locations\":[]}",
        responseBody);

  }

  @Test
  void shouldCreateErrorResponseWhenStartDateGreaterThanEndDate()
      throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/user-management/user/locations";

    String urlWithParams =
        baseUrl + "?userId=buuyby3-4d3d-dff5d-efff3f-3biufbhiu3if&dateTimeStart="
            + "2022-03-09%2000:05:09&dateTimeEnd=2022-01-09%2022:05:06";
    ResponseEntity<ErrorResponseDto> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, ErrorResponseDto.class);
    ErrorResponseDto responseBody = response.getBody();

    assertNotNull(responseBody);
    assertEquals("dateTimeStart cannot be greater then dateTimeEnd",
        responseBody.getFriendlyMessage());

  }

  @Test
  void shouldGetEmptyResponseWhenUserIdNotPresent()
      throws URISyntaxException {
    String baseUrl = "http://localhost:" + randomServerPort + "/user-management/user/locations";

    String urlWithParams = baseUrl + "?userId=invalid-id&dateTimeStart="
        + "2022-01-09%2000:05:09&dateTimeEnd=2022-01-09%2022:05:06";
    ResponseEntity<String> response = restTemplate
        .exchange(new URI(urlWithParams), HttpMethod.GET, null, String.class);
    String responseBody = response.getBody();

    assertNull(responseBody);

  }

  @Test
  void shouldPersistUser() throws URISyntaxException, JsonProcessingException {
    String baseUrl = "http://localhost:" + randomServerPort + "/user-management/user/";

    Map<String, String> bodyParamMap = new HashMap<>();

    bodyParamMap.put("userId", "newId");
    bodyParamMap.put("createdOn", "2022-06-09T22:35:06.000+00:00");
    bodyParamMap.put("email", "abc@gmail.com");
    bodyParamMap.put("firstName", "Pam");
    bodyParamMap.put("secondName", "Brown");

    String reqBodyData = new ObjectMapper().writeValueAsString(bodyParamMap);

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> requestEntity = new HttpEntity<>(reqBodyData, headers);

    ResponseEntity<String> response = restTemplate
        .exchange(new URI(baseUrl), HttpMethod.POST, requestEntity, String.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());

  }

}
