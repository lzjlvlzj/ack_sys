package org.ack.util;

import org.junit.Test;

import java.math.BigDecimal;

public class NumberTest {

    @Test
    public void testBigDecimal(){
        BigDecimal decimal = new BigDecimal(125.6);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(decimal);

    }
}
