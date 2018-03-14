package com.kakaopay.coupon.util;

import org.junit.Test;

public class TestUtilTest {

    @Test
    public void mean() {
        double mean = TestUtil.mean(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(mean);
        assert mean == 5.0;
    }

    @Test
    public void standardDeviation() {
        double std = TestUtil.standardDeviation(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(std);
        assert std == Math.sqrt(60/9.0);
    }
}