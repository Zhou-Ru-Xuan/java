package com.zhouruxuan.generic;


import entity.*;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-24
 **/
public class GenericsTest {
    @Test
    public void testUpperBoundWildcards() {
        List<Dog> dogs = new ArrayList<>();
        // 不会报错
        int i = countLegs(dogs);
        // 报错
        //countLegs1(dogs);
    }

    private int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    private int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }


    @Test
    public void testUpperBoundWildcardsInProcess() {
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

    public void processElements(List<? extends A> elements) {
        for (A a : elements) {
            System.out.println(a.getValue());
        }
    }

    @Test
    public void testUpperBoundWildcardsInRemove() {
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

    @Test
    public void testBridge() {
        // Node<Object> node = new Node<>(5);
        // node.setData("hello");
        MyNode mn = new MyNode(5);
        Node n = mn;            // A raw type - compiler throws an unchecked warning
        n.setData("Hello");     // Causes a ClassCastException to be thrown.
    }

    @Test
    public void testCast() {
        ArrayList<A> as = new ArrayList<>();
        as.add(new A());
        printA(as);
    }

    private <T> void printA(List<T> aList){
       for (T item : aList) {
           Class<T> clazz = (Class<T>) item.getClass();
           System.out.println(clazz);
           System.out.println(item.getClass());
       }
    }
}

@Getter
@Setter
class A {
    private Integer value;
}

class C extends A {
}

class B extends A {
}