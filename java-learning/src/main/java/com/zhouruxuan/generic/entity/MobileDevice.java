package com.zhouruxuan.generic.entity;

import java.util.ArrayList;
import java.util.List;

public class MobileDevice<T> {
    //private static T os; //compile-error

    // ...
    //public static <E> void rtti(List<E> list) {
    //    if (list instanceof ArrayList<E>) {  // compile-time error
    //        // ...
    //    }
    //}

    public static <E> void rtti(List<?> list) {
        if (list instanceof ArrayList<?>) {
            // ...
        }
    }
}