package com.zhouruxuan.jvm.datatype;

import org.junit.jupiter.api.Test;

public class DataTypeTest {
    @Test
    public void testBoolean(){
        boolean flag = true;
        if (flag) {
            System.out.println("Hello Java");
        }

        if(flag == true){
            System.out.println("Hello JVM");
        }
    }
}
