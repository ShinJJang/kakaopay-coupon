package com.kakaopay.coupon.core;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

/*
    https://codereview.stackexchange.com/questions/159421/generate-16-digit-unique-code-like-product-serial
 */
@Component
public class CodeGenerator {

    private static final int COUPON_LENGTH = 16;
    private static final int COUPON_SPACEING = 4;
    private static final char COUPON_SPACER_CHAR = '-';
    protected static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private final Random rng = new SecureRandom();

    char randomChar(){
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }

    String randomUUID(int length, int spacing, char spacerChar){
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        while(length > 0){
            if(spacer == spacing){
                sb.append(spacerChar);
                spacer = 0;
            }
            length--;
            spacer++;
            sb.append(randomChar());
        }
        return sb.toString();
    }

    public String generateCode() {
        return randomUUID(COUPON_LENGTH, COUPON_SPACEING, COUPON_SPACER_CHAR);
    }
}
