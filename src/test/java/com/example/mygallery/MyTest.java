package com.example.mygallery;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2019/4/23 15:50
 * description:
 */
public class MyTest {
    @Test
    public void name() {
        long totalElements = 3206L;
        long page = BigDecimal.valueOf(totalElements).divide(BigDecimal.valueOf(30), 0, BigDecimal.ROUND_HALF_UP).longValue();
        System.out.println("page = " + page);
    }
}
