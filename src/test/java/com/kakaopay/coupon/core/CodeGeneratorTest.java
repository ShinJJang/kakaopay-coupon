package com.kakaopay.coupon.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(code).hasSize(19);
        code = code.replaceAll("-", "");
        for (String chr : code.split("")) {
            assertThat(CodeGenerator.ALPHABET).contains(chr);
        }
    }
}