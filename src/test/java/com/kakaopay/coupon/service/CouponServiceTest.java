package com.kakaopay.coupon.service;

import com.kakaopay.coupon.model.Coupon;
import lombok.extern.java.Log;
import org.junit.Before;
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

    @Before
    public void init() {
        Coupon dummy = couponService.create("1@gmail.com");
        log.info("dummy: " + dummy);

        Coupon checkDummy = couponService.get(1L);
        log.info("check dummy: " + checkDummy);
        assertThat(checkDummy).isNotNull();
    }

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
        String email = "makao.rule@gmail.com";
        Coupon coupon = couponService.getLastByEmail(email);
        assertThat(coupon).isNotNull();
        log.info("getByEmail: " + coupon);
        assertThat(coupon.getEmail()).isEqualTo(email);
    }

    @Test
    public void getList() {
        Page<Coupon> couponPage = couponService.getList(new PageRequest(0, 10));
        log.info("getList: " + couponPage);
        log.info("getList: " + couponPage.getContent());
        assertThat(couponPage).isNotNull();
    }

    @Test
    public void getList_페이징_테스트() {
        int dummyCount = 39;
        int pageSize = 10;

        Page<Coupon> beforeInsert = couponService.getList(new PageRequest(0, pageSize));
        long totalBeforeInsert = beforeInsert.getTotalElements();
        int lastPage = (int) Math.ceil((totalBeforeInsert + dummyCount) / 10f);
        log.info("getList_페이징_테스트 totalBeforeInsert: " + totalBeforeInsert);
        log.info("getList_페이징_테스트 lastPage: " + lastPage);

        for(int i=0; i<dummyCount; i++) {
            couponService.create(i+ "@gmail.com");
        }
        Page<Coupon> couponPage = couponService.getList(new PageRequest(lastPage - 1, pageSize)); // page 0-index
        assertThat(couponPage).isNotNull();
        log.info("getList_페이징_테스트 getNumberOfElements: " + couponPage.getNumberOfElements());
        log.info("getList_페이징_테스트 getTotalElements: " + couponPage.getTotalElements());
        assertThat(couponPage.getTotalElements()).isEqualTo(totalBeforeInsert + dummyCount);
        assertThat(couponPage.getNumberOfElements())
                .isEqualTo((int) totalBeforeInsert + dummyCount - pageSize*(lastPage - 1));
        assertThat(couponPage.getTotalPages()).isEqualTo(lastPage);
    }

    @Test
    public void get() {
        Coupon coupon = couponService.get(1L);
        assertThat(coupon).isNotNull();
        log.info("get: " + coupon);
        assertThat(coupon.getId()).isEqualTo(1L);
    }
}