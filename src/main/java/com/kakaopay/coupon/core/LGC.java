package com.kakaopay.coupon.core;

// https://github.com/bit101/lcg/blob/master/lcg.js
public class LGC {

    /**
     * https://ko.wikipedia.org/wiki/선형_합동_생성기
     *
     * 선형 합동 생성기(LGC, Linear congruential generator)
     *
     * CSPRNG는 아니지만 Well512의 구현에 사용하고자 함
     *
     * seed(n+1) = (a * seed(n) + c) % m
     *
     * 0 < m
     * 0 < a < m
     * 0 < c < m
     * 0 < seed(0) < m
     *
     * 제약 조건
     * 1. c와 m이 서로소
     * 2. a - 1이 m의 모든 소인수로 나눠져야 한다.
     * 3. m이 4의 배수일 경우, a - 1도 4로 나눠져야 한다.
     *
     */
    public static final int a = 1664525;
    public static final int c = 1013904223;
    public static final long m = (long) Math.pow(2, 32);

    private long seed;

    public LGC() {
        this.seed = System.currentTimeMillis();
    }

    public LGC(long seed) {
        this.seed = seed;
    }

    public int nextInt() {
        this.seed = (a * seed + c) % m;
        return (int) seed;
    }
}
