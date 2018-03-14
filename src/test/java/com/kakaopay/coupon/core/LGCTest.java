package com.kakaopay.coupon.core;

import com.kakaopay.coupon.util.TestUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LGCTest {

    private LGC lgc = new LGC();

    @Test
    public void nextInt() {
        Map<Integer, Integer> counter = new HashMap<>();
        for(int i = 0; i < 1000000; i++){
            int n = lgc.nextInt();
            Integer count = counter.get(n);
            if (count == null) {
                counter.put(n, 1);
            } else {
                counter.put(n, ++count);
            }
            System.out.println("i: " + i + ", val: " + n);
        }

        Collection<Integer> counterList = counter.values();
        double[] counterListDouble = new double[counterList.size()];
        Iterator<Integer> iterator = counterList.iterator();
        for (int i = 0; i < counterList.size(); i++) {
            counterListDouble[i] = iterator.next();
        }
        double meanVal = TestUtil.mean(counterListDouble);
        double stdVal = TestUtil.standardDeviation(counterListDouble);
        System.out.println("mean: " + meanVal);
        System.out.println("std:" + stdVal);
    }
}