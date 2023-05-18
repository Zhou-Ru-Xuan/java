package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //System.out.println(System.getProperty("user.dir"));
        //
        //System.out.println("Hello world!");
        //
        //List<Long> longs = Arrays.asList(321321L);
        //HashMap<Long, String> longStringHashMap = new HashMap<>();
        //longs.forEach(l -> {
        //    if (!longStringHashMap.containsKey(l)) {
        //        longStringHashMap.put(l, "321");
        //    }
        //});
        //System.out.println(longStringHashMap);

        //String[] array = {"1", "2", "3", "4", "5"};
        //List<String> list = Arrays.asList(array);
        //list.add("6");


        Map<String, Object> map = new HashMap<>();
        Long id = 100L;
        map.put("id", id);
        map.put("test",new Test());
        System.out.println(map.get("test"));

        Object id1 = map.get("id");
        System.out.println(id1);
    }


}