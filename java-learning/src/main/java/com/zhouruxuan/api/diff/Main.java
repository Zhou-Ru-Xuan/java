package com.zhouruxuan.api.diff;

import com.alibaba.fastjson.JSON;
import entity.Son;

public class Main {
    public static void main(String[] args) throws Exception {
        Son son = new Son();
        Son son1 = new Son();
        son.setName("son");
        son.setAge(123);
        son.setSchoolId(1234L);
        son1.setName("son");
        son1.setAge(123);
        son1.setSchoolId(12345L);
        System.out.println(DiffUtil.isMatching(son,son1));
        toJsonString(son1);
    }

    public static void toJsonString(Object obj){
        System.out.println(JSON.toJSONString(obj));
    }
}
