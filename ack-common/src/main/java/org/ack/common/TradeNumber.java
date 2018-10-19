package org.ack.common;


import java.util.Date;

public class TradeNumber {

    public static String getTradeNumber() {
        long t = System.currentTimeMillis();
        return t + "";
    }
    public static String getTradeNumber(Date date) {
        long t = date.getTime();
        return t + "";
    }
}
