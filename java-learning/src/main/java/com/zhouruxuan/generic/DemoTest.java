package com.zhouruxuan.generic;

import com.zhouruxuan.generic.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-24
 **/
public class DemoTest {
    static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    @Test
    public void test01() {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        countLegs(dogs);
        // 报错
        //countLegs1(dogs);
    }

    public void processElements(List<? extends A> elements) {

        for (A a : elements) {

            System.out.println(a.getValue());

        }

    }

    @Test
    public void test02() {
        List<A> listA = new ArrayList<A>();

        processElements(listA);


        List<B> listB = new ArrayList<B>();

        processElements(listB);


        List<C> listC = new ArrayList<C>();

        processElements(listC);

        //listA = listB;  //编译异常

        //listB = listA; //编译异常

        List<? extends A> temp = listA;

        List<? extends A> temp2 = new ArrayList<>();
        //listA = temp2; //编译异常
    }


    @Test
    public void test03() {
        List<EvenNumber> le = new ArrayList<>();
        List<? extends NaturalNumber> ln = le;
        //ln.add(new NaturalNumber(35));  // compile-time error
        ln.add(null);
        ln.clear();
        Iterator<? extends NaturalNumber> iterator = ln.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }

    }


}
