package com.kakaopay.coupon.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeGeneratorTest {

    @Autowired
    private CodeGenerator generator;

    @Test
    public void randomChar() {
        char chr = generator.randomChar();
        assertThat(CodeGenerator.ALPHABET).contains(String.valueOf(chr));
    }

    @Test
    public void randomUUID() {
        String code = generator.randomUUID(16, 4, '-');
        log.info(code);
        assertThat(code).hasSize(19);
        code = code.replaceAll("-", "");
        for (String chr : code.split("")) {
            assertThat(CodeGenerator.ALPHABET).contains(chr);
        }
    }

    @Test
    public void randomUUUD_run_million_times() {
        int l = 1000000;
        int collision = 0;
        Map<String, Integer> counter = new HashMap<>();
        while(l > 0) {
            String code = generator.randomUUID(16, 4, '-');
            if (counter.containsKey(code)) {
                collision++;
            } else {
                counter.put(code, 1);
            }
            log.info("{}: {}", l, code);
            l--;
        }
        log.info("collision: {}", collision);
    }
}