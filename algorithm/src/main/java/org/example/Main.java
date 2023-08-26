package org.example;


import java.io.IOException;

public class Main {
    static Solution solution = new Solution();

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));

        System.out.println(Long.valueOf(null));

    }

    private void test1(){
        System.out.println(this.getClass().getName());
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}

class Solution {
}