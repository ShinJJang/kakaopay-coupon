package com.kakaopay.coupon.controller;

import com.kakaopay.coupon.error.ErrorController;
import com.kakaopay.coupon.error.ErrorInfo;
import com.kakaopay.coupon.error.exception.EmptyEmailException;
import com.kakaopay.coupon.error.exception.InvalidEmailException;
import com.kakaopay.coupon.error.exception.NotExistCouponException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CouponControllerFailTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCoupon_Invalid_ID() throws Exception {
        ResponseEntity<ErrorInfo> response = restTemplate.getForEntity("/api/v1/coupon/invalid_id", ErrorInfo.class);
        log.info("getCoupon_Invalid_ID: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrorCode()).isEqualTo(ErrorController.ARG_TYPE_MISMATCH);
    }

    @Test
    public void getCoupon_Not_Exist() throws Exception {
        ResponseEntity<ErrorInfo> response = restTemplate.getForEntity("/api/v1/coupon/100", ErrorInfo.class);
        log.info("getCoupon_Not_Exist: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody().getErrorCode()).isEqualTo(NotExistCouponException.errorCode);
    }

    // If size or page is not number, use default value. But not sort parameter.
    @Test
    public void getCouponListWithPage_Invalid_Page_Info() throws Exception {
        ResponseEntity<ErrorInfo> response = restTemplate.getForEntity("/api/v1/coupon?sort=wrong", ErrorInfo.class);
        log.info("getCouponListWithPage_Invalid_Page_Info: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrorCode()).isEqualTo(ErrorController.INVALID_PAGINATION);
    }

    @Test
    public void createCoupon_Null_Body() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(null, headers);

        ResponseEntity<ErrorInfo> response = restTemplate.postForEntity("/api/v1/coupon", request, ErrorInfo.class);
        log.info("createCoupon_Null_Body: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrorCode()).isEqualTo(ErrorController.NULL_BODY);
    }

    @Test
    public void createCoupon_Empty_Email() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String> map = new HashMap<>();

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<ErrorInfo> response = restTemplate.postForEntity("/api/v1/coupon", request, ErrorInfo.class);
        log.info("createCoupon_Empty_Email: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrorCode()).isEqualTo(EmptyEmailException.errorCode);
    }

    @Test
    public void createCoupon_Invalid_Email() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String> map = new HashMap<>();
        String email = "This is not email";
        map.put("email", email);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<ErrorInfo> response = restTemplate.postForEntity("/api/v1/coupon", request, ErrorInfo.class);
        log.info("createCoupon_Invalid_Email: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getErrorCode()).isEqualTo(InvalidEmailException.errorCode);
    }

    @Test
    public void createCoupon_Unsupported_Media_Type() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        String email = "valid@gmail.com";
        map.add("email", email);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<ErrorInfo> response = restTemplate.postForEntity("/api/v1/coupon", request, ErrorInfo.class);
        log.info("createCoupon_Unsupported_Media_Type: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        assertThat(response.getBody().getErrorCode()).isEqualTo(ErrorController.NOT_JSON_MEDIA_TYPE);
    }
}