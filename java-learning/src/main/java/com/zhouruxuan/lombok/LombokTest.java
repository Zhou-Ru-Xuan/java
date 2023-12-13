package com.zhouruxuan.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class LombokTest {
    /**
     * 测试@Data是否覆盖原来的getset方法
     */
    @Test
    public void testData() {
        A a = new A(1);
        System.out.println(a.getA());
        a.setA(2);
        System.out.println(a.getA());
    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
class A{
    private int a;

    public int getA() {
        System.out.println("getA()");
        return a;
    }

    public void setA(int a) {
        System.out.println("setA()");
        this.a = a;
    }
}