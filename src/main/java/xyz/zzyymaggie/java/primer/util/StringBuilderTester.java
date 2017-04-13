package xyz.zzyymaggie.java.primer.util;

import org.apache.commons.lang3.StringUtils;

public class StringBuilderTester {
    private static final int count = 100000;

    /**
     * String + is too long, so we divided by 100
     */
    public static void stringTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        String test = new String();
        for (int i = 0; i < count / 100; i++) {
            test = test + " add ";
        }
        end = System.currentTimeMillis();
        System.out.println((end - begin) + " millis has elapsed when used String. ");
    }

    public static void stringBufferTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        StringBuffer test = new StringBuffer();
        for (int i = 0; i < count; i++) {
            test = test.append(" add ");
        }
        test.toString();
        end = System.currentTimeMillis();
        System.out.println((end - begin) + " millis has elapsed when used StringBuffer. ");
    }

    public static void stringBuilderTest() {
        long begin, end;
        begin = System.currentTimeMillis();
        StringBuilder test = new StringBuilder();
        for (int i = 0; i < count; i++) {
            test = test.append(" add ");
        }
        test.toString();
        end = System.currentTimeMillis();
        System.out.println((end - begin) + " millis has elapsed when used StringBuilder. ");
    }

    public static void joinTest() {
        long begin, end;
        String[] array = new String[count];
        for(int i=0;i<count;i++) {
            array[i] = " add ";
        }
        begin = System.currentTimeMillis();
        StringUtils.join(array);
        end = System.currentTimeMillis();
        System.out.println((end - begin) + " millis has elapsed when used StringUtils.join. ");
    }
    
    public static void main(String[] args) {
        stringTest();
        stringBufferTest();
        stringBuilderTest();
        joinTest();
    }
}
