package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(System.getProperty("user.dir"));
        File file = new File("abc.txt");
        System.out.println(file.getAbsolutePath());
    }
}