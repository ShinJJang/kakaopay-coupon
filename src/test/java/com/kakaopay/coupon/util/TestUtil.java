package com.kakaopay.coupon.util;

public class TestUtil {

    public static double mean(double[] array) {
        double sum = 0.0;

        for (double d : array) sum += d;

        return sum / array.length;
    }

    // 모집단 표준편차
    public static double standardDeviation(double[] array) {
        if (array.length < 2) return Double.NaN;

        double sum = 0.0;
        double sd;
        double diff;
        double meanValue = mean(array);

        for (double d : array) {
            diff = d - meanValue;
            sum += diff * diff;
        }
        sd = Math.sqrt(sum / array.length);

        return sd;
    }

}
