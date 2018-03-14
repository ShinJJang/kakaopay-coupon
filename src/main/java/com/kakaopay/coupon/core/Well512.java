package com.kakaopay.coupon.core;

// https://github.com/js42721/fastrandom/blob/master/src/main/java/fastrandom/WELL512.java
public class Well512 {
    private int[] state = new int[16];
    private int index = 0;

    public Well512() {
        LGC lgc = new LGC();

        for (int i = 0; i < 16; i++) {
            state[i] = lgc.nextInt();
        }
    }

    public int next(int maxValue) {
        return (int) (next() % maxValue);
    }

    private long next() {
        int z1 = state[index];
        int z2 = state[(index + 13) & 0xf];
        int z3 = (z1 ^ (z1 << 16)) ^ (z2 ^ (z2 << 15));
        z1 = state[(index + 9) & 0xf];
        z2 = z1 ^ (z1 >>> 11);
        z1 = state[index] = z2 ^ z3;
        index = (index + 15) & 0xf;
        int z4 = state[index];
        int z5 = (z4 ^ (z4 << 2))
                ^ (z3 ^ (z3 << 18))
                ^ (z2 << 28)
                ^ (z1 ^ ((z1 << 5) & 0xda442d24));
        state[index] = z5;
        return Long.parseLong(Integer.toUnsignedString(z5));
    }

}
