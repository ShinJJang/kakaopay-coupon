package com.kakaopay.coupon.service;

import com.kakaopay.coupon.core.CodeGenerator;
import com.kakaopay.coupon.model.Coupon;
import com.kakaopay.coupon.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepo;

    @Autowired
    private CodeGenerator codeGenerator;

    private static final int TRY_COUNT_IN_COLLISION = 5;

    @Transactional(readOnly = true)
    public Coupon get(Long id) {
        return couponRepo.getOne(id);
    }

    @Transactional(readOnly = true)
    public Page<Coupon> getList(Pageable pageable) {
        return couponRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Coupon> getByEmail(String email) {
        return couponRepo.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Coupon getLastByEmail(String email) {
        return couponRepo.findByEmailOrderByIdDesc(email);
    }

    @Transactional
    public Coupon create(String email) {
        String code = generateUniqueCode();
        if (StringUtils.isEmpty(code)) {
            throw new RuntimeException("Fail to create Coupon. Code is null or empty string.");
        }
        Coupon coupon = new Coupon(email, code);
        couponRepo.save(coupon);
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
                throw new RuntimeException("Fail to create Coupon. Collision occur more than 5 in code generator.");
            }
        }
        return code;
    }
}
