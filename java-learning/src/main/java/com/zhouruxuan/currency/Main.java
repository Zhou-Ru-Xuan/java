package com.zhouruxuan.currency;

public class Main {
    public static void main(String[] args) {
        System.out.println(calc(1,2,'+'));
    }

    static int calc(int a, int b, char op) {
        int c = 0;
        if (op == '+')
            c = a + b;
        else if (op == '-')
            c = a - b;
        else if (op == '*')
            c = a * b;
        else if (op == '/')
            c = a / b;
        else
            System.out.println("invalid operation");
        return c;
    }
}
