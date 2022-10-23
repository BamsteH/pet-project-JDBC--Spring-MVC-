package com.example.demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationPointCalculatorTest {


    @Test
    void getStartPointLimit() {
        int act = PaginationPointCalculator.getStartPointLimit(1, 10);
        int exp = 0;
        Assertions.assertEquals(exp, act);
    }


    @Test
    void getStartPointLimitSecondPage() {
        int act = PaginationPointCalculator.getStartPointLimit(2, 10);
        int exp = 10;
        Assertions.assertEquals(exp, act);
    }


    @Test
    void getStartPointLimitAnotherLimit() {
        int act = PaginationPointCalculator.getStartPointLimit(1, 20);
        int exp = 0;
        Assertions.assertEquals(exp, act);
    }


    @Test
    void getStartPointLimitZeroPage() {
        int act = PaginationPointCalculator.getStartPointLimit(0, 0);
        int exp = 0;
        Assertions.assertEquals(exp, act);
    }
}