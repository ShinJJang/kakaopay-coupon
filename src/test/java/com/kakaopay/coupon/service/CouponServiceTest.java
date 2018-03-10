package com.kakaopay.coupon.service;

import com.kakaopay.coupon.model.Coupon;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Test
    public void create() {
        Coupon coupon = couponService.create("makao.rule@gmail.com");
        assertThat(coupon).isNotNull();

        Coupon couponFromDB = couponService.getLastByEmail("makao.rule@gmail.com");
        assertThat(couponFromDB).isNotNull();
        log.info("db: " + couponFromDB.getCreatedAt().getTime() + ", init: " + coupon.getCreatedAt().getTime());
        // In assertJ, timestamp is different with date
        assertThat(couponFromDB.getCreatedAt().getTime()).isEqualTo(coupon.getCreatedAt().getTime());
        assertThat(couponFromDB).isEqualToIgnoringGivenFields(coupon, "createdAt");
    }

    @Test
    public void getByEmail() {
        Coupon coupon = couponService.getLastByEmail("makao.rule@gmail.com");
        assertThat(coupon).isNotNull();
        log.info("getByEmail: " + coupon);
    }

    @Test
    public void getList() {
        Page<Coupon> coupons = couponService.getList(new PageRequest(1, 10));
        assertThat(coupons).isNotNull();
        log.info("getList: " + coupons.getContent());
    }

    @Test
    public void get() {
        Coupon coupon = couponService.get(1L);
        assertThat(coupon).isNotNull();
        log.info("get: " + coupon);
    }
}