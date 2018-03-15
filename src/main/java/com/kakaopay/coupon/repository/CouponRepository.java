package com.kakaopay.coupon.repository;

import com.kakaopay.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Coupon findByEmail(String email);

    boolean existsByCode(String code);
    boolean existsByEmail(String email);
}
