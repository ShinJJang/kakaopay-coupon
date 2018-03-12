package com.kakaopay.coupon.controller;

import com.kakaopay.coupon.model.Coupon;
import com.kakaopay.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CouponControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CouponService couponService;

    @Before
    public void init() {
        Coupon dummy = couponService.create("1@gmail.com");
        log.info("dummy: " + dummy);

        Coupon checkDummy = couponService.get(1L);
        log.info("check dummy: " + checkDummy);
        assertThat(checkDummy).isNotNull();
    }

    @Test
    public void getCoupon() throws Exception {
        ResponseEntity<Coupon> response = restTemplate.getForEntity("/api/v1/coupon/1", Coupon.class);
        log.info("getCoupon API: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getEmail()).isEqualTo("1@gmail.com");
    }

    @Test
    public void getCouponListWithPage() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/coupon", String.class);
        log.info("getCouponListWithPage API: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // One element is created at @Before
        assertThat(response.getBody()).contains("\"totalElements\":1");
    }

    @Test
    public void createCoupon() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String> map = new HashMap<>();
        String email = "2@gmail.com";
        map.put("email", email);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<Coupon> response = restTemplate.postForEntity("/api/v1/coupon", request, Coupon.class);
        log.info("createCoupon API: " + response);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getEmail()).isEqualTo(email);
    }
}