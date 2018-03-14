package com.kakaopay.coupon.service;

import com.kakaopay.coupon.core.CodeGenerator;
import com.kakaopay.coupon.error.exception.CodeCollisionException;
import com.kakaopay.coupon.model.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceFailTest {

    @Autowired
    private CouponService couponService;

    @SpyBean
    private CodeGenerator codeGenerator;

    /*
        This block is before define mock method for fail, thus normally work.
     */
    @Before
    public void init() {
        Coupon dummy = couponService.create("1@gmail.com");
        log.info("dummy: " + dummy);

        Coupon checkDummy = couponService.get(1L);
        log.info("check dummy: " + checkDummy);
        assertThat(checkDummy).isNotNull();
    }

    /*
        When generated code is duplicated, Retry durability is 5.
        Check sql select log called fifth for duplicated check.
     */
    @Test(expected = CodeCollisionException.class)
    public void create_collision_durability_max_5() {
        given(codeGenerator.generateCode()).willReturn("same code");
        couponService.create("makao.rule@gmail.com");
        couponService.create("makao.rule@gmail.com"); // occur collision
    }
}