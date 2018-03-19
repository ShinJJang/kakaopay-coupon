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
            int randomNumber = rng.next(CodeGenerator.ALPHABET.length());
            Integer count = counter.get(randomNumber);
            System.out.println(randomNumber);
            if (count == null) {
                counter.put(randomNumber, 1);
            } else {
                counter.put(randomNumber, ++count);
            }

            assert 0 <= randomNumber && randomNumber < CodeGenerator.ALPHABET.length() ;
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
        System.out.println("loop / 62 = " + (10000/62f));   // loop / 62 = 161.29033
        System.out.println("mean: " + meanVal);             // mean: 161.29032258064515
        System.out.println("std: " + stdVal);                // std: 12.94702244605
    }
}