package com.kakaopay.coupon.controller;

import com.kakaopay.coupon.model.Coupon;
import com.kakaopay.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.GET)
    public Coupon getCoupon(@PathVariable Long id) {
        return couponService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/coupon", method = RequestMethod.GET)
    public Page<Coupon> getCouponListWithPage(Pageable pageable) {
        return couponService.getList(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    public Coupon createCoupon(@RequestParam String email) {
        return couponService.create(email);
    }
}
