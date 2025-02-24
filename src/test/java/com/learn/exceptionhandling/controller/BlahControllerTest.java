package com.learn.exceptionhandling.controller;

import com.learn.exceptionhandling.exception.AnnotatedBlahException;
import com.learn.exceptionhandling.exception.BlahErrorResponse;
import com.learn.exceptionhandling.exception.BlahException;
import jakarta.servlet.ServletException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlahControllerTest {

    // more about String Testing with MockMvc here:
    // - https://spring.io/guides/gs/testing-web
    // - https://www.baeldung.com/spring-mvc-test-exceptions

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Nested
    @DisplayName("test scenarios with AnnotatedBlahException")
    class AnnotatedBlahExceptionTests {

        @Test
        @SneakyThrows
        public void test_Controller_with_AnnotatedBlahException () {
            MockHttpServletRequestBuilder requestBuilder = get("/blah/annotated-blah-exception")
                    .contentType(MediaType.APPLICATION_JSON);

            // if controller throws an exception,
            // we do not test jason in the response value
            // this depends on how it's being processed by framework.
            // https://www.baeldung.com/spring-mvc-test-exceptions
            // we just assert the thrown Exception class:
            mockMvc.perform(requestBuilder)
                    // .andDo(print()) // this is optional
                    .andExpect(status().is4xxClientError())
                    .andExpect(result -> assertInstanceOf(AnnotatedBlahException.class, result.getResolvedException()));
        }

        @Test
        public void test_error_response_for_AnnotatedBlahException () {
            // if controller throws an exception
            // but, we still want to test how it's being mapped to a response,
            // we should use restTemplate:
            BlahErrorResponse errorResponse = restTemplate.getForObject(
                    "http://localhost:{port}/blah/annotated-blah-exception",
                    BlahErrorResponse.class, port);

            assertNotNull(errorResponse.timestamp());
            assertEquals(418, errorResponse.status());
            assertEquals("I'm a teapot", errorResponse.error());
            assertEquals("Трясця, я - анотований чайник!", errorResponse.message());
            assertEquals("/blah/annotated-blah-exception", errorResponse.path());
        }
    }

    @Nested
    @DisplayName("test scenarios with BlahException")
    class BlahExceptionTests {

        @Test
        @SneakyThrows
        public void test_Controller_with_BlahException () {
            MockHttpServletRequestBuilder requestBuilder = get("/blah/blah-exception")
                    .contentType(MediaType.APPLICATION_JSON);

            mockMvc.perform(requestBuilder)
                    .andExpect(status().is4xxClientError())
                    .andExpect(result -> assertInstanceOf(BlahException.class, result.getResolvedException()));
        }

        @Test
        public void test_error_response_for_BlahException () {
            BlahErrorResponse errorResponse = restTemplate.getForObject(
                    "http://localhost:{port}/blah/blah-exception",
                    BlahErrorResponse.class, port);

            assertNotNull(errorResponse.timestamp());
            assertEquals(418, errorResponse.status());
            assertEquals("I'm a teapot", errorResponse.error());
            assertEquals("Blah, i am a teapot!", errorResponse.message());
            assertEquals("/blah/blah-exception", errorResponse.path());
        }
    }

    @Nested
    @DisplayName("test scenarios with UnknownException")
    class UnknownExceptionTests {

        @Test
        public void test_Controller_with_UnknownBlahException () {
            MockHttpServletRequestBuilder requestBuilder = get("/blah/unknown-blah-exception")
                    .contentType(MediaType.APPLICATION_JSON);

            // this is an example of bad exception handling:
            assertThrows(ServletException.class, () -> {
                mockMvc.perform(requestBuilder);
            }, "Blah, i am an unknown teapot!");
        }

        @Test
        public void test_error_response_for_UnknownBlahException () {
            BlahErrorResponse errorResponse = restTemplate.getForObject(
                    "http://localhost:{port}/blah/unknown-blah-exception",
                    BlahErrorResponse.class, port);

            assertNotNull(errorResponse.timestamp());
            assertEquals(500, errorResponse.status());
            assertEquals("Internal Server Error", errorResponse.error());
            assertEquals("Blah, i am an unknown teapot!", errorResponse.message());
            assertEquals("/blah/unknown-blah-exception", errorResponse.path());
        }
    }

    @Nested
    class UserTests {

        @Test
        public void test_user_access () {
            String expiredJwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9." +
                    "eyJzdWIiOiJibGFoLXVzZXIiLCJleHAiOjE3NDAzNTM2MjIsImlhdCI6MTc0MDM1MzU2Mn0." +
                    "gXCdsHdrNsK80dIwYAzIiC78uIteujERUTdJWHNTRnMhh_-rKn7BlTn3x1fxwXVhHah2Sc5zySJwBx6ADmW_Ww";

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + expiredJwtToken);
            HttpEntity<String> request = new HttpEntity<String>(headers);
            BlahErrorResponse errorResponse = restTemplate.exchange(
                    "http://localhost:{port}/blah/test/user",
                    HttpMethod.GET, request,
                    BlahErrorResponse.class, port).getBody();

            assertNotNull(errorResponse.timestamp());
            assertEquals(401, errorResponse.status());
            assertTrue(errorResponse.message().contains("The Token has expired"));
            assertEquals("/blah/test/user", errorResponse.path());
        }
    }

}