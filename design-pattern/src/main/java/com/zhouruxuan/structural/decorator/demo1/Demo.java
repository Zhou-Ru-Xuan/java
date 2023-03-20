package com.zhouruxuan.structural.decorator.demo1;

import com.zhouruxuan.structural.decorator.demo1.decorators.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Encoding and compression decorators
 * This example shows how you can adjust the behavior of an object without changing its code.
 * <p>
 * Initially, the business logic class could only read and write data in plain text. Then we created several small wrapper classes that add new behavior after executing standard operations in a wrapped object.
 * <p>
 * The first wrapper encrypts and decrypts data, and the second one compresses and extracts data.
 * <p>
 * You can even combine these wrappers by wrapping one decorator with another.
 */
public class Demo {

    private static final String PATH = "/Users/zhouruxuan/Documents/code/idea/java/design-pattern/src/main/java/com/zhouruxuan/structural/decorator/demo1/out";

    private static final String RelativePath = System.getProperty("user.dir") + "/src/main/java/com/zhouruxuan/structural/decorator/demo1/out";

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource(RelativePath + "/OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource(PATH + "/OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}