package com.kakaopay.coupon.service;

import com.kakaopay.coupon.core.CodeGenerator;
import com.kakaopay.coupon.error.exception.CodeCollisionException;
import com.kakaopay.coupon.error.exception.EmptyCodeException;
import com.kakaopay.coupon.error.exception.EmptyEmailException;
import com.kakaopay.coupon.error.exception.NotExistCouponException;
import com.kakaopay.coupon.model.Coupon;
import com.kakaopay.coupon.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepo;

    @Autowired
    private CodeGenerator codeGenerator;

    private static final int TRY_COUNT_IN_COLLISION = 5;

    @Transactional(readOnly = true)
    public Coupon get(Long id) {
        Coupon coupon = couponRepo.findOne(id);
        if (coupon == null) {
            throw new NotExistCouponException("Not exist coupon with id : " + id);
        }
        return coupon;
    }

    @Transactional(readOnly = true)
    public Page<Coupon> getList(Pageable pageable) {
        return couponRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Coupon> getByEmail(String email) {
        return couponRepo.findByEmail(email);
    }

    // Using for test
    @Transactional(readOnly = true)
    protected Coupon getLastByEmail(String email) {
        return couponRepo.findByEmailOrderByIdDesc(email);
    }

    @Transactional
    public Coupon create(String email) {
        if (StringUtils.isEmpty(email)) {
            log.info("CouponService - create : empty email");
            throw new EmptyEmailException("Fail to create Coupon. Email is null or empty.");
        }
        String code = generateUniqueCode();
        if (StringUtils.isEmpty(code)) {
            log.warn("CouponService - create : empty code");
            throw new EmptyCodeException("Fail to create Coupon. Code is null or empty.");
        }
        Coupon coupon = new Coupon(email, code);
        couponRepo.save(coupon);
        log.info("CouponService - create : success with coupon code : {}", coupon.getCode());
        return coupon;
    }

    @Transactional(readOnly = true)
    private String generateUniqueCode() {
        int tryCount = TRY_COUNT_IN_COLLISION;
        String code = null;
        while (tryCount  > 0) {
            code = codeGenerator.generateCode();
            if (!couponRepo.existsByCode(code)) {
                break;
            }
            tryCount--;
            if (tryCount == 0) {
                throw new CodeCollisionException("Fail to create Coupon. Collision occur more than 5 in code generator.");
            }
        }
        return code;
    }
}
