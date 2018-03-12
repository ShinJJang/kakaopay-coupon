package com.kakaopay.coupon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakaopay.coupon.error.exception.InvalidEmailException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String email;
    @NonNull
    @Column(unique = true)
    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    public Coupon(String email, String code) {
        this.email = email;
        this.code = code;
        this.createdAt = new Date();
    }

    @Data
    @NoArgsConstructor
    public static class CouponCreateDTO {
        @NonNull
        @Email(message = InvalidEmailException.errorCode)
        String email;
    }
}
