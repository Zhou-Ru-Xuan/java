package com.zhouruxuan.api.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomTest {

    static int time = 10000;

    /**
     * https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
     *
     * @param n
     * @return
     */
    static String getAlphaNumericString(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index = (int) (AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    @Test
    public void testIntRandom() {
        Set<Integer> set = new HashSet<>(time);
        for (int i = 0; i < time; i++) {
            Random random = new Random();
            int i1 = random.nextInt(10000);
            set.add(i1);
        }
        System.out.println((double) set.size() / time);
    }

    @Test
    public void testGetAlphaNumericString() {
        Set<String> set = new HashSet<>(time);
        for (int i = 0; i < time; i++) {
            String generatedString = getAlphaNumericString(4);
            set.add(generatedString);
        }
        System.out.println((double) set.size() / time);
    }

    /**
     * https://www.baeldung.com/java-random-string
     */
    @Test
    public void givenUsingApache_whenGeneratingRandomAlphabeticString_thenCorrect() {
        Set<String> set = new HashSet<>(time);
        for (int i = 0; i < time; i++) {
            String generatedString = RandomStringUtils.randomAlphabetic(4);
            set.add(generatedString);
        }
        System.out.println((double) set.size() / time);
    }

    @Test
    public void givenUsingApache_whenGeneratingRandomAlphanumericString_thenCorrect() {
        Set<String> set = new HashSet<>(time);
        for (int i = 0; i < time; i++) {
            String generatedString = RandomStringUtils.randomAlphanumeric(4);
            set.add(generatedString);
        }
        System.out.println((double) set.size() / time);
    }

}
