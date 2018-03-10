package com.kakaopay.coupon.service;

import com.kakaopay.coupon.model.Coupon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Test
    public void create() {
        Coupon coupon = couponService.create("makao.rule@gmail.com");
        assertThat(coupon).isNotNull();

        Coupon couponFromDB = couponService.getLastByEmail("makao.rule@gmail.com");
        assertThat(couponFromDB).isNotNull();
        assertThat(couponFromDB).isEqualToComparingFieldByField(coupon);
    }

    @Test
    public void getByEmail() {
        Coupon coupon = couponService.getLastByEmail("makao.rule@gmail.com");
        assertThat(coupon).isNotNull();
    }
}