package com.kakaopay.coupon.model.dto;

import com.kakaopay.coupon.error.exception.InvalidEmailException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CouponCreateDTO {

    @NonNull
    @Email(message = InvalidEmailException.errorCode)
    String email;
}