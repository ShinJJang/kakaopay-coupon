package com.kakaopay.coupon.repository;

import com.kakaopay.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findByEmail(String email);
    Coupon findByEmailOrderByIdDesc(String email);
    Coupon findByCode(String code);

    boolean existsByCode(String code);
}
