package com.zhouruxuan.exception;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-11
 **/
public class ExceptionTest {
    @Test
    public void test01() {
        //System.out.println(t1());
        //System.out.println(t11());
        //System.out.println(t2());
        //System.out.println(t3());
        //System.out.println(t4());

        try {
            try {
                throw new RuntimeException("来自try块中的异常");
            } finally {
                System.out.println("finally");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    /**
     * try和finally中都发生了异常
     */
    @Test
    public void testTryFinallyException() {
        try {
            try {
                throw new RuntimeException("来自try块中的异常");
            } finally {
                throw new RuntimeException("来自finally块中的异常");
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private int t1() {
        try {
            System.out.println("try块代码运行了");
            return 0;
        } catch (Exception e) {
            System.out.println("catch块代码运行了");
        } finally {
            System.out.println("finally块代码运行了");
        }
        return 1;
    }

    private List<Integer> t11() {
        List<Integer> list = new ArrayList<>();
        try {
            list.add(0);
            System.out.println("try:" + list);
            return list;
        } catch (Exception e) {
            list.add(1);
            System.out.println("catch:" + list);
        } finally {
            list.add(2);
            System.out.println("finally:" + list);
        }
        return list;
    }

    private int t2() {
        try {
            System.out.println("try块代码运行了");
            int x = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch块代码运行了");
            return 0;
        } finally {
            System.out.println("finally块代码运行了");
        }
        return 1;
    }

    private int t3() {
        try {
            System.out.println("try块代码运行了");
            return 0;
        } catch (Exception e) {
            System.out.println("catch块代码运行了");
        } finally {
            System.out.println("finally块代码运行了");
            return 1;
        }
    }

    private int t4() {
        try {
            System.out.println("try块代码运行了");
            int x = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch块代码运行了");
            return 0;
        } finally {
            System.out.println("finally块代码运行了");
            return 1;
        }
    }

    private int t5() {
        try {
            System.out.println("try块代码运行了");
            //int x = 1 / 0 ;
            return 0;
        } catch (Exception e) {
            System.out.println("catch块代码运行了");
            return 1;
        } finally {
            System.out.println("finally块代码运行了");
            return 2;
        }
    }

    @Test
    public void testRuntimeException() {
        try {
            throw new IllegalStateException();
        } catch (Exception e) {
            System.out.println("catch");
        }
    }
}
