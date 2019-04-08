package com.cafh.poc.customer.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsUtilTests {

    private StatisticsUtil statisticsUtil;

    @Before
    public void setUp() {
        statisticsUtil = new StatisticsUtil();
    }

    @Test
    public void testGetAverage() {
        Double result = statisticsUtil.getAverage(Arrays.asList(34,12,21,35,45,48,33,25));
        assertEquals(result, 31.625, 0);
    }

    @Test
    public void testGetVariance() {
        Double result = statisticsUtil.getVariance(Arrays.asList(34,12,21,35,45,48,33,25));
        assertEquals(result, 143.98214285714286, 0);
    }

    @Test
    public void testGetStDev() {
        Double result = statisticsUtil.getStDev(Arrays.asList(34,12,21,35,45,48,33,25));
        assertEquals(result, 11.99925592931257, 0);
    }

}