package com.kakaopay.coupon.core;

import com.kakaopay.coupon.util.TestUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Well512Test {

    private Well512 rng = new Well512();

    @Test
    public void next() {
        int l = 10000;
        Map<Integer, Integer> counter = new HashMap<>();
        while(l > 0) {
            int randomNumber = rng.next(61);
            Integer count = counter.get(randomNumber);
            System.out.println(randomNumber);
            if (count == null) {
                counter.put(randomNumber, 1);
            } else {
                counter.put(randomNumber, ++count);
            }

            assert 0 <= randomNumber && randomNumber < 61 ;
            l--;
        }

        Collection<Integer> counterList = counter.values();
        double[] counterListDouble = new double[counterList.size()];
        Iterator<Integer> iterator = counterList.iterator();
        for (int i = 0; i < counterList.size(); i++) {
            counterListDouble[i] = iterator.next();
        }
        double meanVal = TestUtil.mean(counterListDouble);
        double stdVal = TestUtil.standardDeviation(counterListDouble);
        System.out.println("loop / 61 = " + (10000/61f));   // 10000/61 = 163.93443
        System.out.println("mean: " + meanVal);             // mean: 163.9344262295082
        System.out.println("std:" + stdVal);                // std:13.457728418235531
    }
}