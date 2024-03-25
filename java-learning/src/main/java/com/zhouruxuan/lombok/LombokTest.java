package com.zhouruxuan.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

public class LombokTest {
    /**
     * 测试@Data是否覆盖原来的getset方法
     */
    @Test
    public void testData() {
        A a = new A(1, 2);
        System.out.println(a.getA());
        a.setA(2);
        System.out.println(a.getA());

    }

    /**
     * 测试Builder.Default注解的效果
     */
    @Test
    public void testBuilderDefault() {
        A a1 = new A();
        Assert.assertEquals(1, a1.getA());

        A build = A.builder().build();
        Assert.assertEquals(1, build.getA());
        Assert.assertEquals(0, build.getB());
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class A {

    @Builder.Default
    private int a = 1;

    private int b = 2;

    public int getA() {
        System.out.println("getA()");
        return a;
    }

    public void setA(int a) {
        System.out.println("setA()");
        this.a = a;
    }
}